package ma.youcoude.batiCuisine.ui.Menu.subMenus;

import ma.youcoude.batiCuisine.ui.Menu.MenuBlueprint;
import ma.youcoude.batiCuisine.ui.Processes.ProjectSubMenuProcess;

public class ProjectSubMenu extends MenuBlueprint {
    private final ProjectSubMenuProcess projectSubMenuProcess;

    public ProjectSubMenu() {
        projectSubMenuProcess = new ProjectSubMenuProcess();
    }

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
                projectSubMenuProcess.handleFetchingAllProjectsProcess();
                break;
            case 2 :
                projectSubMenuProcess.handleUpdatingProjectProcess();
                break;
            case 3 :
                projectSubMenuProcess.handleProjectDeletionProcess();
                break;
            case 4 :
                return;
            default:
                System.out.println("Invalid choice !");
        }
    }
}
