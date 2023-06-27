package not.File_Server.utils;

import lombok.RequiredArgsConstructor;
import not.File_Server.dto.request.CustomerSignUpDto;
import not.File_Server.dto.response.CustomerDto;
import not.File_Server.impl.AdminServiceImpl;
import not.File_Server.model.Customer;
import not.File_Server.model.Role;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class CustomerConvertor implements Converter<Customer, CustomerDto> {
    private final AdminServiceImpl adminService;
    private final PasswordEncoder passwordEncoder;
    @Override
    public CustomerDto convert(Customer customer) {
        return CustomerDto.builder()
                .fullName(customer.getFullName())
                .email(customer.getEmail())
                .password(customer.getPassword())
                .build();
    }

    public Customer convert(CustomerSignUpDto CustomerSignUpDto) {
        return Customer.builder()
                .fullName(CustomerSignUpDto.getFullName())
                .email(CustomerSignUpDto.getEmail())
                .password(passwordEncoder.encode(CustomerSignUpDto.getPassword()))
                .role(Role.USER)
                .build();
    }

}
