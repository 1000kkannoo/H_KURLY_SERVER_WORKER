package dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Dto;

import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Entity.Worker;
import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Entity.WorkerList;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor

@Builder
public class WorkerDto {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class idSearchRequest{
        private String userId;
        private String pnum;
    }
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class idSearchRepsonse{
        private String userId;
        public static WorkerDto.idSearchRepsonse repsonse (@NotNull Worker worker){
            return idSearchRepsonse.builder()
                    .userId(worker.getUserId())
                    .build();
        }
    }

}
