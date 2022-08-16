package dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Controller;

import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Dto.NoticeDto;
import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Dto.WorkerRegisterDto;
import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Entity.Notice;
import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.lang.constant.Constable;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/notice")
@Slf4j
public class NoticeController {

    private final NoticeService noticeService;

    @PostMapping("add")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public Constable addNotice(
            @Valid @RequestBody final NoticeDto.addNoticeRequest request, HttpServletRequest headerRequest
    ) {
        return noticeService.addNotice(request, headerRequest);
    }

    @GetMapping("list")
    public Object readNotice(HttpServletRequest headerRequest){
        return noticeService.readNotice(headerRequest);
    }
}
