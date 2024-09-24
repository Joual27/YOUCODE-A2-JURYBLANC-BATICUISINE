package ma.youcoude.batiCuisine.ui.Menu;

import java.util.Scanner;

public abstract class MenuBlueprint {

    private final Scanner sc = new Scanner(System.in);

    public abstract void displayMenu();

    public int getMenuChoice(){
        int choice = -1;
        while (choice < 1 || choice > 5 ){
            if(sc.hasNextInt()){
                choice = sc.nextInt();

                if(choice < 1 || choice > 5){
                    System.out.println("Invalid choice ,PLease enter a number between 1 and 5 ");
                }
            }
            else{
                System.out.println("PLease enter a number");
                sc.next();
            }
        }

        return choice;
    }

    public abstract void handleChoice(int choice);

    public void startMenu(){
        int choice;
        do{
            displayMenu();
            choice = getMenuChoice();
            handleChoice(choice);
        }while(choice != 4);
    }
}
