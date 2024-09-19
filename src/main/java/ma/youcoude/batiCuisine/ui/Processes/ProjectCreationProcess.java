package ma.youcoude.batiCuisine.ui.Processes;

import ma.youcoude.batiCuisine.customer.Customer;
import ma.youcoude.batiCuisine.customer.CustomerService;
import ma.youcoude.batiCuisine.customer.interfaces.CustomerServiceI;
import ma.youcoude.batiCuisine.exceptions.CustomerNotFoundException;
import ma.youcoude.batiCuisine.utils.Validator;

import java.util.Scanner;

public class ProjectCreationProcess {
    private final Scanner scanner = new Scanner(System.in);
    private final CustomerServiceI customerService = new CustomerService();

    public void handleAssociatingCustomerToProject() {
       while (true) {
           System.out.println("----------------Associating customer to project -------------\n");
           System.out.println("1 - Search for existing Customer :");
           System.out.println("2 - Add New Customer :");
           int choice = scanner.nextInt();
           scanner.nextLine();
           if(choice == 1) {
               handleSearchingForExistingCustomer();
               break;
           }
           else if(choice == 2) {
               handleAddingNewCustomer();
               break;
           }
           else {
               System.out.println("Invalid choice , TRY AGAIN !");
           }
       }

    }

    public void handleSearchingForExistingCustomer() {
        System.out.println("-------Searching For Customer--------\n");
        System.out.println("Please enter the Full Name of the customer: ");
        String fullName = scanner.nextLine().trim();
        Customer existingCustomer = new Customer();
        try{
            existingCustomer = customerService.getCustomerByFullName(fullName);
            System.out.println("Customer found !");
            System.out.println("Full Name : " + existingCustomer.getFullName());
            System.out.println("Address : " + existingCustomer.getAddress());
            System.out.println("Phone Number : " + existingCustomer.getPhoneNumber());
        }
        catch(CustomerNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    public void handleAddingNewCustomer() {
        System.out.println("-------Adding New Customer--------\n");
        System.out.println("Please enter the Full Name of the customer: ");
        String fullName = scanner.nextLine().trim();
        System.out.println("Please enter customer's address :");
        String address = scanner.nextLine().trim();
        String phoneNumber;
        while(true){
            System.out.println("Please enter customer's phone number: ");
            phoneNumber = scanner.nextLine().trim();
            if(Validator.validatePhoneNumber(phoneNumber)){
                break;
            }
            else{
                System.out.println("Invalid phone number, TRY AGAIN!");
            }
        }
        boolean isPro = handleIsProfessionalValue();
        Customer newCustomer = new Customer();
        newCustomer.setFullName(fullName);
        newCustomer.setAddress(address);
        newCustomer.setPhoneNumber(phoneNumber);
        newCustomer.setProfessional(isPro);

        Customer createdCustomer = customerService.saveCustomer(newCustomer);
        System.out.println("Customer " + createdCustomer.getFullName() + " Created Successfully!");
    }


    public boolean handleIsProfessionalValue(){
        while(true){
            System.out.println("Is this Customer professional ?");
            System.out.println("1 - Yes");
            System.out.println("2 - No");
            int choice = scanner.nextInt();
            if(choice == 1){
                return true;
            }
            else if(choice == 2){
                return false;
            }
            else{
                System.out.println("Invalid choice, TRY AGAIN!");
            }
        }

    }

}
