package ma.youcoude.batiCuisine.customer.interfaces;

import ma.youcoude.batiCuisine.customer.Customer;

import java.util.List;

public interface CustomerServiceI {
    public Customer getCustomerByFullName(String fullName);

    public Customer saveCustomer(Customer customer);

    public List<Customer> getAllCustomers();

    public Customer updateCustomer(Customer customer);

    public Customer deleteCustomer(Customer customer);
}
