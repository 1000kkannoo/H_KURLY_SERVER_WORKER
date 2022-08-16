package dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Repository;

import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record,Long> {
}
