package not.File_Server.config;

import lombok.RequiredArgsConstructor;
import not.File_Server.model.Admin;
import not.File_Server.model.Customer;
import not.File_Server.repositories.AdminRepository;
import not.File_Server.repositories.CustomerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final AdminRepository adminRepository;
    private final CustomerRepository customerRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return email -> {
            Optional<Admin> admin = adminRepository.findByEmail(email);
            Optional<Customer> customer = customerRepository.findByEmail(email) ;
            if (admin.isPresent()) {
                return admin.get();
            } else if (customer.isPresent()) {
                return customer.get();
            } else {
                throw new UsernameNotFoundException("User not found");
            }
        };
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
