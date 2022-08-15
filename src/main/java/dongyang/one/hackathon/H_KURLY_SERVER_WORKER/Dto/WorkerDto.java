package dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Dto;

import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Entity.Worker;
import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Entity.WorkerList;
import lombok.*;

import javax.validation.constraints.NotNull;

public class WorkerDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class phoneNumRequest{
        private String pnum;
    }
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class idSearchRequest{
        private String name;
        private String pnum;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class pwChangeRequest{
        private String userId;
        private String pw;
        private String newPw;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class idSearchRepsonse{
        private String userId;
        private String pw;
        public static WorkerDto.idSearchRepsonse repsonse (@NotNull Worker worker){
            return idSearchRepsonse.builder()
                    .userId(worker.getUserId())
                    .build();
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class pwResponse{
        private String pw;
        public static WorkerDto.pwResponse pwResponse (@NotNull Worker worker){
            return pwResponse.builder()
                    .pw(worker.getPw())
                    .build();
        }
    }

}
