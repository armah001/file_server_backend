package not.File_Server.dto.request;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class AdminLogInDto {
    String email ;
    String password;
}
