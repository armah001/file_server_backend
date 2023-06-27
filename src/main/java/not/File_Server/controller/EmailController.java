package not.File_Server.controller;

import not.File_Server.dto.request.EmailRequestDto;
import not.File_Server.interfaces.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<String> sendEmailWithAttachment(@RequestParam("to") String to,
                                                          @RequestParam("subject") String subject,
                                                          @RequestParam("body") String body,
                                                          @RequestParam("attachment") MultipartFile attachment) {
        try {
            EmailRequestDto emailRequest = new EmailRequestDto();
            emailRequest.setTo(to);
            emailRequest.setSubject(subject);
            emailRequest.setBody(body);
            emailRequest.setAttachment(attachment);

            emailService.sendEmail(emailRequest);

            return ResponseEntity.ok("Email sent successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send email.");
        }
    }
}

