package ma.youcoude.batiCuisine.customer.interfaces;

import ma.youcoude.batiCuisine.customer.Customer;

import java.util.Optional;

public interface CustomerRepositoryI {
    public Optional<Customer> findCustomerByFullName(String fullName);

    public void saveCustomer(Customer customer);

}
