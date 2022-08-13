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

    // 논리적 설계의 FK ( 근무자 ID )
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String workTime;

    private String workPlace;

    private String workType;

    private String workDay;

    private Boolean con;

    private Boolean edu;

    @NotNull
    private Character arrangement;

    @NotNull
    private Integer wscore;

    @NotNull
    private Integer wcnt;

    // 논리적 설계의 FK ( 이름 )
    @NotNull
    private String name;

    // 논리적 설계의 FK ( 전화번호 )
    @NotNull
    private String pnum;

}
