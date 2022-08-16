package dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Dto;

import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Entity.WorkerList;
import lombok.*;

import javax.validation.constraints.NotNull;


public class WorkerListDto {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class WorkerListResponse{
        private String workTime;
        private String workPlace;
        private String workType;
        private String workDay;
        private Boolean con;
        private Boolean edu;
        private Character arrangement;
        private Integer wscore;
        private Integer wcnt;
        public static WorkerListDto.WorkerListResponse Response(@NotNull WorkerList workerList) {
            return WorkerListDto.WorkerListResponse.builder()
                    .workTime(workerList.getWorkTime())
                    .workPlace(workerList.getWorkPlace())
                    .workType(workerList.getWorkType())
                    .workDay(workerList.getWorkDay())
                    .con(workerList.getCon())
                    .edu(workerList.getEdu())
                    .arrangement(workerList.getArrangement())
                    .wscore(workerList.getWscore())
                    .wcnt(workerList.getWcnt())
                    .build();
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class updateRequest{
        private String pnum;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class choiceRequest{
        private String workTime;
        private String workPlace;
        private String workDay;

    }
}
