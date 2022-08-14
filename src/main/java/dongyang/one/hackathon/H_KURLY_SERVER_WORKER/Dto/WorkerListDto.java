package dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Dto;

import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Entity.WorkerList;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkerListDto {
    private String workTime;
    private String workPlace;
    private String workType;
    private String workDay;
    private Boolean con;
    private Boolean edu;
    private Character arrangement;
    private Integer wscore;
    private Integer wcnt;
    private String name;
    private String pnum;

    public static WorkerListDto Response(@NotNull WorkerList workerList) {
        return WorkerListDto.builder()
                .workTime(workerList.getWorkTime())
                .workPlace(workerList.getWorkPlace())
                .workType(workerList.getWorkType())
                .workDay(workerList.getWorkDay())
                .con(workerList.getCon())
                .edu(workerList.getEdu())
                .arrangement(workerList.getArrangement())
                .wscore(workerList.getWscore())
                .wcnt(workerList.getWcnt())
                .name(workerList.getName())
                .pnum(workerList.getPnum())
                .build();
    }
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class updateRequest{
        private String workTime;
        private String workPlace;
        private String workType;
        private String workDay;
        private Boolean con;
        private Boolean edu;
        private Character arrangement;
        private Integer wscore;
        private Integer wcnt;
        private String pnum;
    }
}
