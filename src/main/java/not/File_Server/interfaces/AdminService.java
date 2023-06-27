package not.File_Server.interfaces;


import not.File_Server.dto.request.AdminLogInDto;
import not.File_Server.dto.request.AdminSignUpDto;
import not.File_Server.model.Admin;

public interface AdminService {
    String signUpAdmin(AdminSignUpDto request);

    String authenticateAdmin(AdminLogInDto request);

    Admin getAdmin(String email);

}
