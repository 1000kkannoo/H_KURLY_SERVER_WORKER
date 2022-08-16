package dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Repository;

import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Entity.Worker;
import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Entity.WorkerList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorkerListRepository extends JpaRepository<WorkerList,Long> {

    Optional<WorkerList> findOneByUserId(String userId);
}
