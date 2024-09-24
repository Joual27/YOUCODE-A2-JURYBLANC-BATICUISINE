package ma.youcoude.batiCuisine.ui.Processes;

import ma.youcoude.batiCuisine.customer.Customer;
import ma.youcoude.batiCuisine.customer.CustomerService;
import ma.youcoude.batiCuisine.customer.interfaces.CustomerServiceI;

import java.util.List;
import java.util.Scanner;

public class CustomerSubMenuProcesses {
    private final Scanner sc;
    private final CustomerServiceI customerService;

    public CustomerSubMenuProcesses() {
        sc = new Scanner(System.in);
        customerService = new CustomerService();
    }

    public void handleFetchingAllCustomersProcess() {
        List<Customer> customers = customerService.getAllCustomers();
        System.out.println("╔══════════════════════════════════════════════════════════════════════════════╗");
        System.out.printf("║ %-20s │ %-30s │ %-13s │ %-14s ║\n", "Full Name", "Address", "Phone Number", "Is Professional");
        System.out.println("╠══════════════════════════════════════════════════════════════════════════════╣");

        for (Customer customer : customers) {
            String fullName = customer.getFullName();
            String address = customer.getAddress();
            String phoneNumber = customer.getPhoneNumber();
            String isProfessional = customer.isProfessional() ? "Yes" : "No";

            System.out.printf("║ %-20s │ %-30s │ %-13s │ %-14s ║\n", fullName, address, phoneNumber, isProfessional);
        }
        System.out.println("╚══════════════════════════════════════════════════════════════════════════════╝");
    }
}
