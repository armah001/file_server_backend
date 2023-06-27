package not.File_Server.interfaces;

import not.File_Server.model.Document;

import java.util.List;

public interface DocumentService {
    void uploadFile(Document fileData);
    Document getFileById(Long fileId);
    List<Document> getAllFiles();
    void deleteFile(Long fileId);
}
