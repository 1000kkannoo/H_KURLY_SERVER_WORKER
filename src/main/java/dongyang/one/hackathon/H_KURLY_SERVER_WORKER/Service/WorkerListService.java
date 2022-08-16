package dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Service;

import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Dto.ListTokenInfoResponseDto;
import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Dto.RecordDto;
import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Dto.TokenInfoResponseDto;
import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Dto.WorkerListDto;
import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Entity.Record;
import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Entity.WorkerList;
import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Model.StatusFalse;
import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Model.StatusTrue;
import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Repository.RecordRepository;
import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Repository.TokenRepository;
import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Repository.WorkerListRepository;
import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Repository.WorkerRepository;
import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.lang.constant.Constable;
import java.util.*;
import java.util.stream.Collectors;

import static dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Model.Model.AUTHORIZATION_HEADER;

@Service
@RequiredArgsConstructor
@Slf4j
public class WorkerListService {
    private final WorkerListRepository workerListRepository;

    private final TokenRepository tokenRepository;

    private final WorkerRepository workerRepository;

    private final RecordRepository recordRepository;

    // validate 및 단순 메소드화

    private TokenInfoResponseDto getTokenInfo() {
        return TokenInfoResponseDto.Response(
                Objects.requireNonNull(SecurityUtil.getCurrentUsername()
                        .flatMap(
                                workerRepository::findOneWithAuthoritiesByUserId)
                        .orElse(null))
        );
    }

    private ListTokenInfoResponseDto getListTokenInfo() {
        return ListTokenInfoResponseDto.Response(
                Objects.requireNonNull(SecurityUtil.getCurrentUsername()
                        .flatMap(
                                workerListRepository::findOneByUserId)
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

    // 근무자 근무리스트 조회
    public List<Object> getWorkerList(HttpServletRequest headerRequest) {
        if (!tokenCredEntialsValidate(headerRequest))
            return Collections.singletonList(StatusFalse.JWT_CREDENTIALS_STATUS_FALSE);

        return workerListRepository
                .findById(getTokenInfo().getId())
                .stream()
                .map(WorkerListDto.WorkerListResponse::Response)
                .collect(Collectors.toList());
    }

    // 근무자 업무선택
    public Constable choiceWorkerList(WorkerListDto.choiceRequest request, HttpServletRequest headerRequest) {
        if (!tokenCredEntialsValidate(headerRequest))
            return StatusFalse.JWT_CREDENTIALS_STATUS_FALSE;

        if (
                !getListTokenInfo().getEdu()
                        || !getListTokenInfo().getCon()
        ) {
            return StatusFalse.ERROR_CHOICE;
        }

        workerListRepository.save(
                WorkerList.builder()
                        .id(getTokenInfo().getId())
                        .userId(getTokenInfo().getUserId())
                        .workDay(request.getWorkDay())
                        .workPlace(request.getWorkPlace())
                        .workTime(request.getWorkTime())
                        .workType(getListTokenInfo().getWorkType())
                        .con(getListTokenInfo().getCon())
                        .edu(getListTokenInfo().getEdu())
                        .arrangement('W')
                        .wscore(getListTokenInfo().getWscore())
                        .wcnt(getListTokenInfo().getWcnt())
                        .build()
        );
        return StatusTrue.WORK_CHOICE_STATUS_TRUE;
    }

    // 근무자 교육 수행
    public Constable eduWorker(WorkerListDto.eduRequest request, HttpServletRequest headerRequest) {
        if (!tokenCredEntialsValidate(headerRequest))
            return StatusFalse.JWT_CREDENTIALS_STATUS_FALSE;

        workerListRepository.save(
                WorkerList.builder()
                        .id(getTokenInfo().getId())
                        .userId(getTokenInfo().getUserId())
                        .workDay(getListTokenInfo().getWorkDay())
                        .workPlace(getListTokenInfo().getWorkPlace())
                        .workTime(getListTokenInfo().getWorkTime())
                        .workType(getListTokenInfo().getWorkType())
                        .con(getListTokenInfo().getCon())
                        .edu(request.getEdu())
                        .arrangement(getListTokenInfo().getArrangement())
                        .wscore(getListTokenInfo().getWscore())
                        .wcnt(getListTokenInfo().getWcnt())
                        .build()
        );
        return StatusTrue.EDU_CHECK_STATUS_TRUE;
    }

    // 근무자 근로 계약 수행
    public Constable conWorker(WorkerListDto.conRequest request, HttpServletRequest headerRequest) {
        if (!tokenCredEntialsValidate(headerRequest))
            return StatusFalse.JWT_CREDENTIALS_STATUS_FALSE;
        workerListRepository.save(
                WorkerList.builder()
                        .id(getTokenInfo().getId())
                        .userId(getTokenInfo().getUserId())
                        .workDay(getListTokenInfo().getWorkDay())
                        .workPlace(getListTokenInfo().getWorkPlace())
                        .workTime(getListTokenInfo().getWorkTime())
                        .workType(getListTokenInfo().getWorkType())
                        .con(request.getCon())
                        .edu(getListTokenInfo().getEdu())
                        .arrangement(getListTokenInfo().getArrangement())
                        .wscore(getListTokenInfo().getWscore())
                        .wcnt(getListTokenInfo().getWcnt())
                        .build()
        );
        return StatusTrue.CON_CHECK_STATUS_TRUE;
    }

    // 출근
    public Constable startWorker(HttpServletRequest headerRequest) {
        if (!tokenCredEntialsValidate(headerRequest))
            return StatusFalse.JWT_CREDENTIALS_STATUS_FALSE;

        if (getListTokenInfo().getArrangement() == 'Y') {
            recordRepository.save(
                    Record.builder()
                            .idx(getTokenInfo().getId())
                            .workPlace(getListTokenInfo().getWorkPlace())
                            .workType(getListTokenInfo().getWorkTime())
                            .wcnt(getListTokenInfo().getWcnt())
                            .build()
            );

            return StatusTrue.START_WORK;
        } else
            return StatusFalse.START_FAILURE;
    }

    // 퇴근
    public Constable endWorker(HttpServletRequest headerRequest) {
        if (!tokenCredEntialsValidate(headerRequest))
            return StatusFalse.JWT_CREDENTIALS_STATUS_FALSE;

        Optional<Long> id = Optional.of(recordRepository
                .findByIdxAndWcnt(getTokenInfo().getId(), getListTokenInfo().getWcnt())
                .get()
                .getId());

        RecordDto getRecordInfo = RecordDto.Response(
                Objects.requireNonNull(id
                        .flatMap(
                                recordRepository::findById)
                        .orElse(null))
        );


        if (getListTokenInfo().getArrangement() == 'Y' && getRecordInfo.getStart() != null) {

            recordRepository.save(
                    Record.builder()
                            .id(getRecordInfo.getId())
                            .idx(getTokenInfo().getId())
                            .workPlace(getListTokenInfo().getWorkPlace())
                            .workType(getListTokenInfo().getWorkTime())
                            .start(getRecordInfo.getStart())
                            .wcnt(getRecordInfo.getWcnt())
                            .build()
            );

            // 퇴근 시 근무상태 초기화
            workerListRepository.save(
                    WorkerList.builder()
                            .id(getTokenInfo().getId())
                            .userId(getTokenInfo().getUserId())
                            .workDay(null)
                            .workPlace(null)
                            .workTime(null)
                            .workType(null)
                            .con(false)
                            .edu(false)
                            .arrangement('N')
                            .wscore(getListTokenInfo().getWscore())
                            .wcnt(getListTokenInfo().getWcnt() + 1)
                            .build()
            );
            return StatusTrue.END_WORK;
        }
        return StatusFalse.END_FAILURE;
    }

    // 현재 신청중인 내역 조회
    public List<Object> WorkingInfo(HttpServletRequest headerRequest) {
        if (!tokenCredEntialsValidate(headerRequest))
            return Collections.singletonList(StatusFalse.JWT_CREDENTIALS_STATUS_FALSE);

        return workerListRepository
                .findById(getTokenInfo().getId())
                .stream()
                .map(WorkerListDto.choiceRequest::Response)
                .collect(Collectors.toList());
    }

    // 과거 근무 내역 조회
    public List<Object> WorkedInfo(HttpServletRequest headerRequest) {
        if (!tokenCredEntialsValidate(headerRequest))
            return Collections.singletonList(StatusFalse.JWT_CREDENTIALS_STATUS_FALSE);

        return recordRepository
                .findAllByIdx(getTokenInfo().getId())
                .stream()
                .map(RecordDto.RecordResponse::recordResponse)
                .collect(Collectors.toList());
    }
}
