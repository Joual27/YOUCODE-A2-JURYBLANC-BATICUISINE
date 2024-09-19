package ma.youcoude.batiCuisine.customer.interfaces;

import ma.youcoude.batiCuisine.customer.Customer;

public interface CustomerServiceI {
    public Customer getCustomerByFullName(String fullName);

    public Customer saveCustomer(Customer customer);
}
