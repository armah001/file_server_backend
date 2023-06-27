package not.File_Server.utils;

import lombok.RequiredArgsConstructor;
import not.File_Server.dto.request.AdminSignUpDto;
import not.File_Server.dto.response.AdminDto;
import not.File_Server.model.Admin;
import not.File_Server.model.Role;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class AdminConvertor implements Converter<Admin, AdminDto>{
    private final PasswordEncoder passwordEncoder;

    @Override
    public AdminDto convert(Admin doctor) {
        return AdminDto.builder()
                .name(doctor.getFullName())
                .email(doctor.getEmail())
                .build();
    }

    public Admin convert(AdminSignUpDto doctorSignUpDto) {
        return Admin.builder()
                .email(doctorSignUpDto.getEmail())
                .password(passwordEncoder.encode(doctorSignUpDto.getPassword()))
                .fullName(doctorSignUpDto.getFullName())
                .role(Role.USER)
                .build();
    }
}
