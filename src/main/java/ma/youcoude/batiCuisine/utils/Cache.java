package ma.youcoude.batiCuisine.utils;

import ma.youcoude.batiCuisine.customer.Customer;

import java.util.HashMap;
import java.util.Map;

public class Cache {
    private final static Map<String, Customer> customerCache = new HashMap<>();

    public static Customer getCustomer(String fullName){
        return customerCache.get(fullName);
    }

    public static void setCustomer(Customer customer){
        customerCache.put(customer.getFullName(), customer);
    }
}
