package dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Controller;

import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Dto.WorkerDto;
import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Dto.WorkerListDto;
import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Dto.WorkerRegisterDto;
import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Service.WorkerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.lang.constant.Constable;
import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@RequiredArgsConstructor
@RequestMapping("worker/auth")
@Slf4j
public class WorkerController {
    private final WorkerService workerService;

    // 로그아웃
    @PostMapping("logout")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public Constable logoutUser(HttpServletRequest headerRequest) {
        return workerService.logoutWorker(headerRequest);
    }

    // 회원가입
    @PostMapping("register")
    public Constable registerUser(
            @Valid @RequestBody final WorkerRegisterDto.Request request
    ) {
        return workerService.registerWorker(request);
    }

    // 휴대폰 인증
    @PostMapping("check")
    public Map<Object, Object> certifiedPhoneNumber(
            @Valid @RequestBody final WorkerDto.phoneNumRequest request
    ) {
        Random rand = new Random();
        String randNum = "";
        for (int i = 0; i < 4; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            randNum += ran;
        }
        return workerService.certifiedPhoneNumber(request,randNum);
    }

    // 회원정보수정 (전화번호 수정)
    @PostMapping("edit")
    public Constable postUpdateUser(
            @Valid @RequestBody final WorkerListDto.updateRequest request ,HttpServletRequest headerRequest
    ) {
        return workerService.updateWorker(request,headerRequest);
    }

    // ID 찾기
    @PostMapping("searchid")
    public List<Object> idSearch(
            @Valid @RequestBody final WorkerDto.idSearchRequest request
    ) {
        return workerService.idSearch(request);
    }

    // 비밀번호 변경
    @PostMapping("searchpw/pedit")
    public Constable pwChangeUser(
            @Valid @RequestBody final WorkerDto.pwChangeRequest request
    ) {
        return workerService.PwChangeWorker(request);
    }

    // 회원 탈퇴
    @PostMapping("delete")
    public Constable deleteUser(
        @Valid @RequestBody final WorkerDto.deleteRequest request, HttpServletRequest headerRequest
    ){
        return workerService.deleteWorker(request,headerRequest);
    }

}
