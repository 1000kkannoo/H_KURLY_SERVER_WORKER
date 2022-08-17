package dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Dto;

import lombok.*;


public class WorkerRegisterDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request{

        private String userId;
        private String pw;
        private String name;
        private String pnum;
        private Character gender;
        private String hnum;

    }
}
