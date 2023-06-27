package not.File_Server.repositories;
import not.File_Server.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DocumentRepository extends JpaRepository<Document, Long>{
        List<Document> findByFilename(String filename);
        List<Document> findByFileType(String fileType);


}
