package dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Dto;

import lombok.*;

import javax.validation.constraints.NotNull;


public class WorkerLoginDto {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request{
        @NotNull
        private String userId;
        @NotNull
        private String pw;
    }
}
