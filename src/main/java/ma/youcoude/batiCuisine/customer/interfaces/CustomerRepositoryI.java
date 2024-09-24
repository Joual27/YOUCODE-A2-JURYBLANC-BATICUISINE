package ma.youcoude.batiCuisine.customer.interfaces;

import ma.youcoude.batiCuisine.customer.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepositoryI {
    public Optional<Customer> findCustomerByFullName(String fullName);

    public void saveCustomer(Customer customer);
    public List<Customer> findAllCustomers();
    public void updateCustomer(Customer customer);
    public void deleteCustomer(String customerFullName);
}
