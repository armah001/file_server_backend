package not.File_Server.impl;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import not.File_Server.config.JwtService;
import not.File_Server.dto.request.AdminLogInDto;
import not.File_Server.dto.request.AdminSignUpDto;
import not.File_Server.exception.UserException;
import not.File_Server.model.Admin;
import not.File_Server.repositories.AdminRepository;
import not.File_Server.interfaces.AdminService;
import not.File_Server.utils.AdminConvertor;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AdminRepository adminRepository;
    private final AdminConvertor adminConvertor;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public String signUpAdmin(AdminSignUpDto request) {
        Optional<Admin> adminEntity = adminRepository.findByEmail(request.getEmail());

        if (adminEntity.isEmpty()) {
            var admin = adminConvertor.convert(request);
            return jwtService.generateToken(adminRepository.save(admin));
        }
        throw new UserException("This admin already exists");
    }

    @Override
    public String authenticateAdmin(AdminLogInDto request) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        var admin = adminRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserException("Admin does not exist"));
        return jwtService.generateToken(admin);
    }

    @Override
    public Admin getAdmin(String email) {
        Optional<Admin> adminOptional = adminRepository.findByEmail(email);
        if (adminOptional.isPresent()) {
            return adminOptional.get();
        }
        throw new UserException("User does not exist");
    }
}
