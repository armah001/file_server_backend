package not.File_Server.model;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "documents")
public class Document {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String filename;

        @Column(nullable = false)
        private String fileType;

        @Lob
        @Column(nullable = false)
        private byte[] data;


        public Document() {
            // Default constructor
        }

    public Document(String filename, String fileType, byte[] data) {
        this.filename = filename;
        this.fileType = fileType;
        this.data = data;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public byte[] getFileData() {
        return data;
    }

    public String getFileName() {
        return filename;
    }
}


