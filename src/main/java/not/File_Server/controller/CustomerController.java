package not.File_Server.controller;

import lombok.AllArgsConstructor;
import not.File_Server.config.JwtService;
import not.File_Server.dto.request.AdminLogInDto;
import not.File_Server.dto.request.CustomerLogInDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import not.File_Server.dto.request.CustomerSignUpDto;
import not.File_Server.dto.response.CustomerDto;
import not.File_Server.interfaces.CustomerService;
import not.File_Server.utils.CustomerConvertor;


@RestController
@RequestMapping("api/v1/customer")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerConvertor customerConvertor;

    private final JwtService jwtService;
    @PostMapping("/signup")
    public ResponseEntity signUpPatient(@RequestBody CustomerSignUpDto request) {
        return ResponseEntity.ok(customerService.signUpCustomer(request));
    }

//    @GetMapping("/auth")
//    public ResponseEntity authenticateCustomer() {
//        return ResponseEntity.ok(customerService.authenticateCustomer());
//    }

    @PostMapping("/login")
    public ResponseEntity authenticate(@RequestBody CustomerLogInDto request) {
        return ResponseEntity.ok(customerService.authenticateCustomer(request));
    }

    @GetMapping("/get")
    public ResponseEntity<CustomerDto> getCustomer(@RequestHeader("Authorization") String authorizationHeader ) {
        String token = authorizationHeader.substring(7);
        String email = jwtService.extractUsername(token);
        return ResponseEntity.ok(customerConvertor.convert(customerService.getCustomer(email)));
    }
}
