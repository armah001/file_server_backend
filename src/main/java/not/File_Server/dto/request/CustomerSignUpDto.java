package not.File_Server.dto.request;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CustomerSignUpDto {
     String fullName;
     String password;
     String email;
}
