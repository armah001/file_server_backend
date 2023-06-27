package not.File_Server.model;
import javax.persistence.*;

@Entity
@Table(name = "email_logs")
public class EmailLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email_id")
    private Long emailId;

    @Column(name = "number_of_emails_sent")
    private int numberOfEmailsSent;

    // Constructors, getters, and setters

    public EmailLog() {
    }

    public EmailLog(Long emailId, int numberOfEmailsSent) {
        this.emailId = emailId;
        this.numberOfEmailsSent = numberOfEmailsSent;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmailId() {
        return emailId;
    }

    public void setEmailId(Long emailId) {
        this.emailId = emailId;
    }

    public int getNumberOfEmailsSent() {
        return numberOfEmailsSent;
    }

    public void setNumberOfEmailsSent(int numberOfEmailsSent) {
        this.numberOfEmailsSent = numberOfEmailsSent;
    }
}

