package dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Controller;

import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Dto.WorkerListDto;
import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Service.WorkerListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.lang.constant.Constable;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("worker")
@Slf4j
public class WorkerListController {

    private final WorkerListService workerListService;

    // 근무자의 근무리스트 조회
    @GetMapping("list")
    public List<Object> getWorkerList(HttpServletRequest request) {
        return workerListService.getWorkerList(request);
    }

    // 업무 신청
    @PostMapping("list/choice")
    public Constable choiceWorkerList(
            @Valid @RequestBody WorkerListDto.choiceRequest request, HttpServletRequest headerRequest
    ) {
        return workerListService.choiceWorkerList(request, headerRequest);
    }

    // 교육 수행
    @PostMapping("list/edu")
    public Constable eduWorker(
            @Valid @RequestBody WorkerListDto.eduRequest request, HttpServletRequest headerRequest
    ) {
        return workerListService.eduWorker(request, headerRequest);
    }

    // 근로 계약
    @PostMapping("list/con")
    public Constable conWorker(
            @Valid @RequestBody WorkerListDto.conRequest request, HttpServletRequest headerRequest
    ) {
        return workerListService.conWorker(request, headerRequest);
    }

    // 출근
    @PostMapping("list/start")
    public Constable startWorker(HttpServletRequest headerRequest)
    {
        return workerListService.startWorker(headerRequest);
    }
}
