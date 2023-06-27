package not.File_Server.dto.response;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class AdminDto {
    String name;
    String email ;
    int password;

}
