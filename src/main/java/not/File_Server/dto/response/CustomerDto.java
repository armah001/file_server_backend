package not.File_Server.dto.response;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CustomerDto {
    String email;
    String fullName;
    String password;

}
