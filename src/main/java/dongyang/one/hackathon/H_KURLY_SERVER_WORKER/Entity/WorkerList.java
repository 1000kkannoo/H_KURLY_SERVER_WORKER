package dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class WorkerList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 논리적 설계의 FK ( USER_ID )
    @NotNull
    private String userId;

    private String workTime;

    private String workPlace;

    private String workType;

    private String workDay;

    @NotNull
    private Boolean con;

    @NotNull
    private Boolean edu;

    @NotNull
    private Character arrangement;

    @NotNull
    private Integer wscore;

    @NotNull
    private Integer wcnt;

}
