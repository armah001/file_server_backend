package not.File_Server.repositories;

import not.File_Server.model.EmailLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailLogRepository extends JpaRepository<EmailLog, Long> {
    int countByEmailId(Long emailId);
}
