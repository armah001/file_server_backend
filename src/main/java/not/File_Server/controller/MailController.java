//package not.File_Server.controller;
//import not.File_Server.model.Customer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.UUID;
//
//@RestController
//public class MailController {
//
//    private final JavaMailSender mailSender;
//
//    @Autowired
//    public MailController(JavaMailSender mailSender) {
//        this.mailSender = mailSender;
//    }
//
//    @PostMapping("/sendActivationEmail")
//    public String sendActivationEmail(@RequestBody Customer customer) {
//        // Replace 'User' with your actual User model that contains user details.
//        // Assuming you have a field called 'email' in your User model.
//
//        // Generate an activation link (you might have your custom logic for this).
//        String activationLink = "https://your-domain.com/activate?token=" + generateActivationToken(customer);
//
//        // Compose the email message.
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setTo(customer.getEmail());
//        mailMessage.setSubject("Account Activation");
//        mailMessage.setText("Hello " + customer.getUsername() + ",\n\n" +
//                "Thank you for signing up. Please click the link below to activate your account:\n\n" +
//                activationLink + "\n\n" +
//                "Best regards,\n" +
//                "Your App Team");
//
//        // Send the email.
//        mailSender.send(mailMessage);
//
//        return "Activation email sent successfully.";
//    }
//
//    private String generateActivationToken(Customer customer) {
//        //we will use UUID.randomUUID().toString() to generate a random token.
//        String token = UUID.randomUUID().toString();
//        customer.setActivationToken(token);
//        return token;
//    }
//}
