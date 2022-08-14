package dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Repository;

import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Entity.workerToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<workerToken, String> {

    boolean existsById(String token);
}
