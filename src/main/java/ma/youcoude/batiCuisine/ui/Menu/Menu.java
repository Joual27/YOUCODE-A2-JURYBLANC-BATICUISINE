package ma.youcoude.batiCuisine.ui.Menu;

import ma.youcoude.batiCuisine.ui.Menu.subMenus.CustomerSubMenu;
import ma.youcoude.batiCuisine.ui.Menu.subMenus.ProjectSubMenu;
import ma.youcoude.batiCuisine.ui.Processes.FetchingAllProjectsProcess;
import ma.youcoude.batiCuisine.ui.Processes.FetchingProjectDetailsProcess;
import ma.youcoude.batiCuisine.ui.Processes.ProjectCreationProcess;

import java.util.Scanner;

public class Menu extends MenuBlueprint{
    private final Scanner scanner ;
    private final ProjectCreationProcess projectCreationProcess ;
    private final FetchingAllProjectsProcess fetchingAllProjectsProcess ;
    private final FetchingProjectDetailsProcess fetchingProjectDetailsProcess ;
    private final CustomerSubMenu customerSubMenu ;
    private final ProjectSubMenu projectSubMenu ;

    public Menu(){
        scanner = new Scanner(System.in);
        projectCreationProcess = new ProjectCreationProcess();
        fetchingAllProjectsProcess = new FetchingAllProjectsProcess();
        fetchingProjectDetailsProcess = new FetchingProjectDetailsProcess();
        customerSubMenu = new CustomerSubMenu();
        projectSubMenu = new ProjectSubMenu();
    }

    @Override
    public void displayMenu(){
        System.out.println("========= Main Menu =========");
        System.out.println("1. Create a new project");
        System.out.println("2. Show All Projects");
        System.out.println("3. Calculate Project Cost");
        System.out.println("4. Handle Available Projects");
        System.out.println("5. Handle available Customers");
        System.out.println("6. Exit");
        System.out.println("=============================");
    }

    @Override
    public int getMenuChoice() {
        int choice = -1;

        while (choice < 1 || choice > 6) {
            System.out.println("Enter your choice ( 1-4 ) ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();

                if (choice < 1 || choice > 6) {
                    System.out.println("Invalid choice ,PLease enter a number between 1 and 5 ");
                }
            } else {
                System.out.println("Please enter a number");
                scanner.next();
            }
        }
        return choice;
    }

    @Override
    public void handleChoice(int choice){
        switch (choice) {
            case 1:
                projectCreationProcess.handleFullProjectCreationProcess();
                break;
            case 2:
                fetchingAllProjectsProcess.handleFetchingAllProjects();
                break;
            case 3:
                fetchingProjectDetailsProcess.handleFetchingProjectDetailsProcess();
                break;
            case 4:
                projectSubMenu.startMenu();
                break;
            case 5 :
                customerSubMenu.startMenu();
                break;
            case 6:
                return;
            default:
                System.out.println("Invalid choice ,PLease enter a number between 1 and 4");
                break;
        }
    }

    @Override
    public void startMenu(){
        int choice;
        do{
            displayMenu();
            choice = getMenuChoice();
            handleChoice(choice);
        }while(choice != 6);
    }


}
