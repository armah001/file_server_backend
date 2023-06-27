package not.File_Server.dto.request;

import lombok.Builder;
import lombok.Value;


@Builder
@Value
public class AdminSignUpDto {
    String fullName;
    String email ;
    String password;

}
