package dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Service;

import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Dto.NoticeDto;
import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Dto.TokenInfoResponseDto;
import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Entity.Notice;
import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Model.StatusFalse;
import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Model.StatusTrue;
import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Repository.NoticeRepository;
import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Repository.TokenRepository;
import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Repository.WorkerRepository;
import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.lang.constant.Constable;
import java.util.Objects;

import static dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Model.Model.AUTHORIZATION_HEADER;

@Service
@RequiredArgsConstructor
@Slf4j
public class NoticeService {

    private final NoticeRepository noticeRepository;

    private final TokenRepository tokenRepository;

    private final WorkerRepository workerRepository;

    // validate 및 단순 메소드화

    private Boolean tokenCredEntialsValidate(HttpServletRequest request) {
        String getToken = request.getHeader(AUTHORIZATION_HEADER);
        if (!tokenRepository.existsById(getToken)) {
            return false;
        }
        return true;
    }

    private TokenInfoResponseDto getTokenInfo() {
        return TokenInfoResponseDto.Response(
                Objects.requireNonNull(SecurityUtil.getCurrentUsername()
                        .flatMap(
                                workerRepository::findOneWithAuthoritiesByUserId)
                        .orElse(null))
        );
    }

    // Service

    public Constable addNotice(NoticeDto.addNoticeRequest request, HttpServletRequest headerRequest) {
        if (!tokenCredEntialsValidate(headerRequest))
            return StatusFalse.JWT_CREDENTIALS_STATUS_FALSE;

        noticeRepository.save(
                Notice.builder()
                        .title(request.getTitle())
                        .text(request.getText())
                        .build()
        );
        return StatusTrue.NOTICE_ADD_STATUS_TRUE;
    }

    public Object readNotice(HttpServletRequest headerRequest) {
        if (!tokenCredEntialsValidate(headerRequest))
            return StatusFalse.JWT_CREDENTIALS_STATUS_FALSE;

        return noticeRepository.findAll();
    }

    public Constable updateNotice(NoticeDto.updateNoticeRequest request, HttpServletRequest headerRequest) {
        if (!tokenCredEntialsValidate(headerRequest))
            return StatusFalse.JWT_CREDENTIALS_STATUS_FALSE;

        noticeRepository.save(
                Notice.builder()
                        .id(request.getId())
                        .title(request.getTitle())
                        .text(request.getText())
                        .build()
        );
        return StatusTrue.UPDATE_ADD_STATUS_TRUE;
    }

    public Constable deleteNotice(NoticeDto.deleteNoticeRequest request, HttpServletRequest headerRequest) {
        if (!tokenCredEntialsValidate(headerRequest))
            return StatusFalse.JWT_CREDENTIALS_STATUS_FALSE;

        noticeRepository.deleteById(request.getId());
        return StatusTrue.DELETE_ADD_STATUS_TRUE;
    }
}
