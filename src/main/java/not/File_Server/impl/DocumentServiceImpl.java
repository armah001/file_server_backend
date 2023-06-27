package not.File_Server.impl;

import not.File_Server.interfaces.DocumentService;
import not.File_Server.model.Document;
import not.File_Server.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {

        @Autowired
        private DocumentRepository fileRepository;

        @Override
        public void uploadFile(Document fileData) {
            fileRepository.save(fileData);
        }

        @Override
        public Document getFileById(Long fileId) {
            return fileRepository.findById(fileId).orElse(null);
        }

        @Override
        public List<Document> getAllFiles() {
            return fileRepository.findAll();
        }

        @Override
        public void deleteFile(Long fileId) {
            fileRepository.deleteById(fileId);
        }

}
