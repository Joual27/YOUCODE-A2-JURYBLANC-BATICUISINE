package ma.youcoude.batiCuisine.ui.Menu;

import ma.youcoude.batiCuisine.ui.Processes.ProjectCreationProcess;

import java.util.Scanner;

public class Menu {
    private final Scanner scanner ;
    private final ProjectCreationProcess projectCreationProcess ;

    public Menu(){
        scanner = new Scanner(System.in);
        projectCreationProcess = new ProjectCreationProcess();
    }

    public void displayMenu(){
        System.out.println("========= Main Menu =========");
        System.out.println("1. Create a new project");
        System.out.println("2. Show All Projects");
        System.out.println("3. Calculate Project Cost");
        System.out.println("4. Exit");
        System.out.println("=============================");
    }

    public int getMenuChoice() {
        int choice = -1;

        while (choice < 1 || choice > 4) {
            System.out.println("Enter your choice ( 1-4 ) ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();

                if (choice < 1 || choice > 4) {
                    System.out.println("Invalid choice ,PLease enter a number between 1 and 5 ");
                }
            } else {
                System.out.println("Please enter a number");
                scanner.next();
            }
        }
        return choice;
    }

    public void handleChoice(int choice){
        switch (choice) {
            case 1:
                projectCreationProcess.handleFullProjectCreationProcess();
                break;
            case 2:
                System.out.println("Show All Projects");
                break;
            case 3:
                System.out.println("Calculate Project Cost");
                break;
            case 4:
                return;
            default:
                System.out.println("Invalid choice ,PLease enter a number between 1 and 4");
                break;
        }
    }

    public void startMenu(){
        int choice;
        do{
            displayMenu();
            choice = getMenuChoice();
            handleChoice(choice);
        }while(choice != 4);
    }


}
