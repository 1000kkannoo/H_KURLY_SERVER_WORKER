package dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "authority_Worker")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorityWorker {

    @Id
    @Column(name = "authority_name", length = 50)
    @NotNull
    private String authorityName;
}
