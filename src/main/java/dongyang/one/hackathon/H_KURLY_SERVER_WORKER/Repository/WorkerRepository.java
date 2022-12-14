package dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Repository;

import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Entity.Worker;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkerRepository extends JpaRepository<Worker, Long> {

    @EntityGraph(attributePaths = "authorities")
    Optional<Worker> findOneWithAuthoritiesByUserId(String userId);

    Optional<Worker> findByUserId(String userId);
    List<Worker> findByNameAndPnum(String name, String pnum);
}
