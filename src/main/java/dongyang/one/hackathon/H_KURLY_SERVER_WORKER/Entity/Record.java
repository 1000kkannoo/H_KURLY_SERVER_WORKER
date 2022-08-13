package dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Entity;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 논리적 설계의 FK ( 근무자 ID )
    @NotNull
    private Long idx;

    private LocalDateTime start;

    private LocalDateTime end;

    private String workPlace;

    private String workType;
}
