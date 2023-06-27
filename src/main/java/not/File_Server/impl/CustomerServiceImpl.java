package not.File_Server.impl;

import lombok.RequiredArgsConstructor;
import not.File_Server.dto.request.CustomerLogInDto;
import not.File_Server.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import not.File_Server.config.JwtService;
import not.File_Server.dto.request.CustomerSignUpDto;
import not.File_Server.dto.response.CustomerDto;
import not.File_Server.exception.UserException;
import not.File_Server.model.Customer;
import not.File_Server.repositories.CustomerRepository;
import not.File_Server.interfaces.AdminService;
import not.File_Server.interfaces.CustomerService;
import not.File_Server.utils.CustomerConvertor;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerConvertor customerConvertor;
    private final AdminService adminService;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final HttpServletRequest request;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public String signUpCustomer(CustomerSignUpDto request) {

        Optional<Customer> patientOptional = customerRepository.findByEmail(request.getEmail());
        if (patientOptional.isEmpty()) {
            return jwtService.generateToken(customerRepository.save(customerConvertor.convert(request)));
        }
        return jwtService.generateToken(customerConvertor.convert(request));
    }

//    @Override
//    public void saveCustomer(CustomerDto customerDto) {
//        Customer customer = new Customer();
//        // Set other customer properties
//        String encodedPassword = passwordEncoder.encode(customerDto.getPassword());
//        customer.setPassword(passwordEncoder.encode(customerDto.getPassword()));
//
//        System.out.println("Encoded Password: " + encodedPassword);
//
//        // Save the customer
//        customerRepository.save(customer);
//    }
    @Override
    public String authenticateCustomer(CustomerLogInDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        var customer = customerRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserException("customer does not exist"));

        // Verify password
        if (!passwordEncoder.matches(request.getPassword(), customer.getPassword())) {
            throw new UserException("Invalid password");
        }

        return jwtService.generateToken(customer);
    }

//    public String authenticateCustomer(CustomerLogInDto request) {
//        authenticationManager
//                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
//
//        var customer = customerRepository.findByEmail(request.getEmail())
//                .orElseThrow(() -> new UserException("customer does not exist"));
//        return jwtService.generateToken(customer);
//    }

    @Override
    public Customer getCustomer() {
        String token = request.getHeader("Authorization").substring(7);
        String Email = jwtService.extractUsername(token);
        Optional<Customer> customerOptional = customerRepository.findByEmail(Email);
        if(customerOptional.isPresent()) {
           return customerOptional.get();
        }
        throw new UserException("User does not exist");
    }

    @Override
    public Customer getCustomer(String customerId) {
        Optional<Customer> customerOptional = customerRepository.findByEmail(customerId);
        if(customerOptional.isPresent()) {
            return customerOptional.get();
        }
        throw new UserException("User does not exist");
    }


}
