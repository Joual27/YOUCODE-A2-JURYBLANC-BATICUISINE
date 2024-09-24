package ma.youcoude.batiCuisine.ui.Menu.subMenus;

import ma.youcoude.batiCuisine.ui.Menu.MenuBlueprint;

public class ProjectSubMenu extends MenuBlueprint {

    @Override
    public void displayMenu(){
        System.out.println("========= Project Menu =========");
        System.out.println("1. View All Projects");
        System.out.println("2. Update Project Data");
        System.out.println("3. Delete Project");
        System.out.println("4. Back To Main Menu");
        System.out.println("=============================");
    }

    @Override
    public void handleChoice(int choice){
        switch (choice) {
            case 1:
        }
    }
}
