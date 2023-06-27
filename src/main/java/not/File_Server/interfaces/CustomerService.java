package not.File_Server.interfaces;

import not.File_Server.dto.request.AdminLogInDto;
import not.File_Server.dto.request.CustomerLogInDto;
import not.File_Server.dto.request.CustomerSignUpDto;
import not.File_Server.dto.response.CustomerDto;
import not.File_Server.model.Customer;

public interface CustomerService {

    String signUpCustomer(CustomerSignUpDto request);

    String authenticateCustomer(CustomerLogInDto request);

    Customer getCustomer();

    Customer getCustomer(String customerId);

//    void saveCustomer(CustomerDto customerDto);

}
