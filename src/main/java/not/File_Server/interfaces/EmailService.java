package not.File_Server.interfaces;

import not.File_Server.dto.request.EmailRequestDto;

public interface EmailService {
    void sendEmail(EmailRequestDto emailRequest);
}
