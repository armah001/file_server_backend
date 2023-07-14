package not.File_Server.controller;

import lombok.AllArgsConstructor;
import not.File_Server.model.Document;
import not.File_Server.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.print.Doc;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/document")
@AllArgsConstructor
public class DocumentController {

        @Autowired
        private DocumentRepository fileRepository;

        @PostMapping
        public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
            try {
                if (file.isEmpty()) {
                    return ResponseEntity.badRequest().body("File is empty");
                }

                // Create an instance of your file model class
                Document fileData = new Document(file.getOriginalFilename(), file.getContentType(), file.getBytes());

                // Save the file data to the database
                Document savedFile = fileRepository.save(fileData);

                return ResponseEntity.ok("File uploaded successfully with ID: " + savedFile.getId());
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
            }
        }

    @GetMapping("/get")
    public ResponseEntity<List<Document>> getAllFiles() {
        List<Document> files = fileRepository.findAll();
        return ResponseEntity.ok(files);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFile(@PathVariable("id") Long fileId) {
        Optional<Document> optionalFile = fileRepository.findById(fileId);
        if (optionalFile.isPresent()) {
            Document file = optionalFile.get();
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
                    .contentType(MediaType.parseMediaType(file.getFileType()))
                    .body(file.getFileData());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    }

