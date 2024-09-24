package ma.youcoude.batiCuisine.ui.Menu.subMenus;

import ma.youcoude.batiCuisine.ui.Menu.MenuBlueprint;
import ma.youcoude.batiCuisine.ui.Processes.CustomerSubMenuProcesses;

public class CustomerSubMenu extends MenuBlueprint {

    private final CustomerSubMenuProcesses customerSubMenuProcesses;

    public CustomerSubMenu() {
        customerSubMenuProcesses = new CustomerSubMenuProcesses();
    }
    @Override
    public void displayMenu(){
        System.out.println("========= Project Menu =========");
        System.out.println("1. View All Customers");
        System.out.println("2. Update Customer Data");
        System.out.println("3. Delete Customer");
        System.out.println("4. Back To Main Menu");
        System.out.println("=============================");
    }

    @Override
    public void handleChoice(int choice){
        switch (choice) {
            case 1:
                customerSubMenuProcesses.handleFetchingAllCustomersProcess();
                break;
            case 2:
                customerSubMenuProcesses.handleUpdatingCustomerProcess();
                break;
            case 3:
                customerSubMenuProcesses.handleCustomerDeletionProcess();
                break;
            case 4:
                return;
            default:
                System.out.println("Invalid choice , PLEASE TRY AGAIN !");
        }
    }
}
