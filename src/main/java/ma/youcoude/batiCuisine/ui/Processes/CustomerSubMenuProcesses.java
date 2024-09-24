package ma.youcoude.batiCuisine.ui.Processes;

import ma.youcoude.batiCuisine.customer.Customer;
import ma.youcoude.batiCuisine.customer.CustomerService;
import ma.youcoude.batiCuisine.customer.interfaces.CustomerServiceI;
import ma.youcoude.batiCuisine.exceptions.CustomerNotFoundException;
import ma.youcoude.batiCuisine.utils.Validator;

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

    public void handleUpdatingCustomerProcess() {
        handleFetchingAllCustomersProcess();
        Customer customerToUpdate = new Customer();
        while(true){
            System.out.println("PLease provide the name of the customer you want to update !");
            String customerName = sc.nextLine();
            try{
                customerToUpdate = customerService.getCustomerByFullName(customerName);
                System.out.println("Customer Found !");
                break;
            }
            catch(CustomerNotFoundException e){
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Current Customer Address: " + customerToUpdate.getAddress());
        System.out.println("Enter a new value or Press enter to keep the same value !");
        String addressValue = sc.nextLine();
        if (!addressValue.trim().isEmpty()) {
            customerToUpdate.setAddress(addressValue);
        }

        while (true){
            System.out.println("Current Customer Phone: " + customerToUpdate.getPhoneNumber());
            System.out.println("Enter a new value or Press enter to keep the same value !");
            String phoneNumberValue = sc.nextLine();
            if (!phoneNumberValue.trim().isEmpty()) {
                if (Validator.validatePhoneNumber(phoneNumberValue)) {
                    customerToUpdate.setPhoneNumber(phoneNumberValue);
                    break;
                }
                else {
                    System.out.println("Invalid phone number , pls try again !");
                }
            }
            else{
                break;
            }
        }
       while (true){
           String professionalStatus = customerToUpdate.isProfessional() ? "Professional" : "Not Professional";
           System.out.println("The current customer is :"+ professionalStatus);
           System.out.println("Enter a new value (Y/N) or Press enter to keep the same value !");
           String professionalValue = sc.nextLine();
           if (!professionalValue.trim().isEmpty()) {
               if (professionalValue.equalsIgnoreCase("y")){
                   customerToUpdate.setProfessional(true);
                   break;
               }
               else if(professionalValue.equalsIgnoreCase("n")){
                   customerToUpdate.setProfessional(false);
                   break;
               }
               else{
                   System.out.println("Invalid choice , pls try again !");
               }
           }
           else{
               break;
           }
       }

       Customer updatedCustomer = customerService.updateCustomer(customerToUpdate);
       System.out.println("Customer " + updatedCustomer.getFullName() + " has been updated successfully !");
    }


    public void handleCustomerDeletionProcess() {
        handleFetchingAllCustomersProcess();
        System.out.println("PLease provide the name of the customer you want to delete !");
        String customerName = sc.nextLine();
        try{
            Customer customerToDelete  = customerService.getCustomerByFullName(customerName);
            Customer deletedCustomer = customerService.deleteCustomer(customerName);
            System.out.println("Customer " + deletedCustomer.getFullName() + " has been deleted successfully !");
        }
        catch(CustomerNotFoundException e){
            e.getMessage();
        }

    }

}
