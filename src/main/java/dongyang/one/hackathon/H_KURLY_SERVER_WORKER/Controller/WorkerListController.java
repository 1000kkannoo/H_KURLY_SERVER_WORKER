package dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Controller;

import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Service.WorkerListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("worker")
@Slf4j
public class WorkerListController {

    private final WorkerListService workerListService;
    // 근무자의 근무일지조회
    @GetMapping("list")
    public List<Object> getWorkerList(HttpServletRequest request) {
        return workerListService.getWorkerList();
    }
}
