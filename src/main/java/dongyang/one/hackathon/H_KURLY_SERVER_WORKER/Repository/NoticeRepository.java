package dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Repository;

import dongyang.one.hackathon.H_KURLY_SERVER_WORKER.Entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice,Long> {
}
