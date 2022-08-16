package dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Service;

import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Dto.TokenInfoResponseDto;
import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Dto.WorkerDto;
import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Dto.WorkerListDto;
import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Dto.WorkerRegisterDto;
import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Entity.AuthorityWorker;
import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Entity.Worker;
import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Entity.WorkerList;
import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Exception.DuplicateManagerException;
import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Model.StatusFalse;
import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Model.StatusTrue;
import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Repository.TokenRepository;
import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Repository.WorkerListRepository;
import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Repository.WorkerRepository;
import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.json.simple.JSONObject;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.lang.constant.Constable;
import java.util.*;
import java.util.stream.Collectors;

import static dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Model.Model.AUTHORIZATION_HEADER;

@Service
@RequiredArgsConstructor
@Slf4j
public class WorkerService {

    private final WorkerListRepository workerListRepository;

    private final TokenRepository tokenRepository;

    private final WorkerRepository workerRepository;

    private final PasswordEncoder passwordEncoder;

    // validate 및 단순 메소드화

    private TokenInfoResponseDto getTokenInfo() {
        return TokenInfoResponseDto.Response(
                Objects.requireNonNull(SecurityUtil.getCurrentUsername()
                        .flatMap(
                                workerRepository::findOneWithAuthoritiesByUserId)
                        .orElse(null))
        );
    }

    private Boolean tokenCredEntialsValidate(HttpServletRequest request) {
        String getToken = request.getHeader(AUTHORIZATION_HEADER);
        if (!tokenRepository.existsById(getToken)) {
            return false;
        }
        return true;
    }

    // Service

    // 휴대폰 인증
    public Map<Object, Object> certifiedPhoneNumber(WorkerDto.phoneNumRequest phoneNumber, String randNum) {

        String api_key = "NCSBDTMXRMDGUIFD";
        String api_secret = "S917YGKP2H2IFYE0P9ONJBTFA2EDCV3J";
        Message coolsms = new Message(api_key, api_secret);

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("to", phoneNumber.getPnum());
        params.put("from", "010-4345-4377");
        params.put("type", "SMS");
        params.put("text", "마켓컬리 근무자 앱 휴대폰인증 : 인증번호는" + "[" + randNum + "]" + "입니다.");
        params.put("app_version", "test app 1.2");

        try {
            JSONObject obj = (JSONObject) coolsms.send(params);
            System.out.println(obj.toString());
        } catch (CoolsmsException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getCode());
        }

        Map<Object, Object> send = new HashMap<>();
        send.put("randNum", randNum);
        return send;
    }

    // 회원가입
    @Transactional
    public Constable registerWorker(WorkerRegisterDto.Request request) {
        if (workerRepository.findOneWithAuthoritiesByUserId(request.getUserId()).orElse(null) != null) {
            throw new DuplicateManagerException("이미 가입되어 있는 유저입니다.");
        }
        AuthorityWorker authority = AuthorityWorker.builder()
                .authorityName("ROLE_USER")
                .build();

        // 근무자 정보 등록
        workerRepository.save(
                Worker.builder()
                        .userId(request.getUserId())
                        .pw(passwordEncoder.encode(request.getPw()))
                        .name(request.getName())
                        .pnum(request.getPnum())
                        .gender(request.getGender())
                        .hnum(request.getHnum())
                        .authorities(Collections.singleton(authority))
                        .build()
        );

        // 근무자 인력현황 정보 등록
        workerListRepository.save(
                WorkerList.builder()
                        .con(false)
                        .edu(false)
                        .arrangement('N')
                        .wscore(100)
                        .wcnt(0)
                        .userId(request.getUserId())
                        .build()
        );

        return StatusTrue.REGISTER_STATUS_TRUE;
    }

    // 회원정보수정을 위한 근무자 인력현황 정보 가져오기
    @Transactional
    public List<Object> getUpdateWorker(HttpServletRequest headerRequest) {

        if (!tokenCredEntialsValidate(headerRequest))
            return Collections.singletonList(StatusFalse.JWT_CREDENTIALS_STATUS_FALSE);

        Long a = getTokenInfo().getId();
        log.info(String.valueOf(a));
        return workerListRepository
                .findById(getTokenInfo().getId())
                .stream()
                .map(WorkerListDto.WorkerListResponse::Response)
                .collect(Collectors.toList());
    }

    // 근무자 인력현황 정보 및 근무자 정보 업데이트
    @Transactional
    public Constable postUpdateWorker(WorkerListDto.updateRequest request, HttpServletRequest headerRequest) {

        if (!tokenCredEntialsValidate(headerRequest))
            return StatusFalse.JWT_CREDENTIALS_STATUS_FALSE;

        AuthorityWorker authority = AuthorityWorker.builder()
                .authorityName("ROLE_USER")
                .build();

        log.info(getTokenInfo().getPw());

        // 근무자 정보 업데이트
        workerRepository.save(
                Worker.builder()
                        .id(getTokenInfo().getId())
                        .userId(getTokenInfo().getUserId())
                        .pw(getTokenInfo().getPw())
                        .name(getTokenInfo().getName())
                        .pnum(request.getPnum())
                        .gender(getTokenInfo().getGender())
                        .hnum(getTokenInfo().getHnum())
                        .authorities(Collections.singleton(authority))
                        .build()
        );

        // 근무자 인력현황 정보 업데이트
        workerListRepository.save(
                WorkerList.builder()
                        .id(getTokenInfo().getId())
                        .workDay(request.getWorkDay())
                        .workPlace(request.getWorkPlace())
                        .workTime(request.getWorkTime())
                        .workType(request.getWorkType())
                        .con(request.getCon())
                        .edu(request.getEdu())
                        .arrangement(request.getArrangement())
                        .wscore(request.getWscore())
                        .wcnt(request.getWcnt())
                        .userId(getTokenInfo().getUserId())
                        .build()
        );

        return StatusTrue.UPDATE_STATUS_TRUE;
    }

    // id 찾기
    @Transactional
    public List<Object> idSearch(WorkerDto.idSearchRequest request) {
        return workerRepository.findByNameAndPnum(request.getName(), request.getPnum())
                .stream()
                .map(WorkerDto.idSearchRepsonse::repsonse)
                .collect(Collectors.toList());
    }

    @Transactional
    // 비밀번호 변경 (userId_아이디로 id 찾는부분)
    public List<Object> getPwChangeWorker(WorkerDto.pwChangeRequest request) {
        if (workerRepository.findByUserId(request.getUserId()).orElse(null) == null) {
            throw new RuntimeException("해당 유저가 없습니다.");
        }
        log.info(request.getUserId());
        return workerRepository.findByUserId(request.getUserId())
                .stream()
                .map(WorkerDto.pwResponse::pwResponse)
                .collect(Collectors.toList());
    }

    // 비밀번호 변경 (변경하는 부분)
    public Constable PwChangeWorker(WorkerDto.pwChangeRequest request) {

        if (passwordEncoder.matches(request.getPw(), request.getOldPw())) // 입력한 비밀번호와 현재 비밀번호가 맞을 경우
        {
            AuthorityWorker authority = AuthorityWorker.builder()
                    .authorityName("ROLE_USER")
                    .build();

            workerRepository.save(
                    Worker.builder()
                            .id(request.getId())
                            .userId(request.getUserId())
                            .pw(passwordEncoder.encode(request.getNewPw()))
                            .name(request.getName())
                            .pnum(request.getPnum())
                            .gender(request.getGender())
                            .hnum(request.getHnum())
                            .authorities(Collections.singleton(authority))
                            .build()
            );

            return StatusTrue.PASSWORD_CHANGE_STATUS_TRUE;
        } else {
            return StatusFalse.PASSWORD_CHANGE_STATUS_FALSE;
        }
    }


    // 회원 탈퇴
    public Constable deleteWorker(WorkerDto.deleteRequest request, HttpServletRequest headerRequest) {
        if (!tokenCredEntialsValidate(headerRequest))
            return StatusFalse.JWT_CREDENTIALS_STATUS_FALSE;

        if (passwordEncoder.matches(request.getPw(), getTokenInfo().getPw())) {

            Long id = getTokenInfo().getId();

            workerRepository.deleteById(id);
            workerListRepository.deleteById(id);

            return StatusTrue.DELETE_STATUS_TRUE;
        } else {
            return StatusFalse.DELETE_STATUS_FALSE;
        }
    }

    public Constable logoutWorker(HttpServletRequest headerRequest) {
        if (!tokenCredEntialsValidate(headerRequest))
            return StatusFalse.JWT_CREDENTIALS_STATUS_FALSE;

        String getToken = headerRequest.getHeader(AUTHORIZATION_HEADER);
        tokenRepository.deleteById(getToken);
        return StatusTrue.LOGOUT_STATUS_TRUE;
    }
}
