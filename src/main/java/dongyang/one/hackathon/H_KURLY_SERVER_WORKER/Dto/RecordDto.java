package dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Entity.Record;
import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Entity.Worker;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecordDto {
    @JsonIgnore
    private Long id;
    private Long idx;
    private LocalDateTime start;
    private LocalDateTime end;
    private String workPlace;
    private String workType;
    private Integer wcnt;


    public static RecordDto Response(@NotNull Record record) {
        return RecordDto.builder()
                .id(record.getId())
                .idx(record.getIdx())
                .workPlace(record.getWorkPlace())
                .workType(record.getWorkType())
                .start(record.getStart())
                .end(record.getEnd())
                .wcnt(record.getWcnt())
                .build();
    }
}
