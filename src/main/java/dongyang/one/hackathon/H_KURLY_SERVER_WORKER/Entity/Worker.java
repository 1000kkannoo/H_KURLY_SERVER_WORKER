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
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String pnum;

    @NotNull
    private String userId;

    @NotNull
    private String pw;

    @NotNull
    private Character gender;

    @NotNull
    private String hnum;

    @ManyToMany
    @JoinTable(
            name = "worker_authority",
            joinColumns = {@JoinColumn(name = "id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<AuthorityWorker> authorities;
}
