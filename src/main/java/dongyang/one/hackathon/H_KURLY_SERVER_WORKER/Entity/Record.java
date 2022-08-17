package dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
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

    @CreatedDate
    private LocalDateTime start;

    @LastModifiedDate
    private LocalDateTime end;

    @NotNull
    private String workPlace;

    @NotNull
    private String workType;

    @NotNull
    private Integer wcnt;
}
