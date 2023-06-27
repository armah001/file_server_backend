package not.File_Server.impl;

import not.File_Server.dto.request.EmailRequestDto;
import not.File_Server.interfaces.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendEmail(EmailRequestDto emailRequest) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(emailRequest.getTo());
            helper.setSubject(emailRequest.getSubject());
            helper.setText(emailRequest.getBody());

            // Attach the file
            if (emailRequest.getAttachment() != null) {
                helper.addAttachment(emailRequest.getAttachment().getOriginalFilename(), emailRequest.getAttachment());
            }

            mailSender.send(message);
        } catch (MessagingException | MailException e) {
            // Handle the exception
        }
    }
}
