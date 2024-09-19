package ma.youcoude.batiCuisine.customer;

import ma.youcoude.batiCuisine.customer.interfaces.CustomerServiceI;
import ma.youcoude.batiCuisine.exceptions.CustomerNotFoundException;

public class CustomerService implements CustomerServiceI {

    private final CustomerRepository customerRepository = new CustomerRepository();

    @Override
    public Customer getCustomerByFullName(String fullName){
        return customerRepository.findCustomerByFullName(fullName)
                .orElseThrow(() -> new CustomerNotFoundException("No customer found with name: " + fullName));
    }

    @Override
    public Customer saveCustomer(Customer customer){
        customerRepository.saveCustomer(customer);
        return customer;
    }
}
