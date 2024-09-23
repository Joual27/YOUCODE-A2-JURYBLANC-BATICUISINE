package ma.youcoude.batiCuisine.ui.Processes;

import ma.youcoude.batiCuisine.component.Component;
import ma.youcoude.batiCuisine.project.Project;
import ma.youcoude.batiCuisine.project.ProjectService;
import ma.youcoude.batiCuisine.project.interfaces.ProjectServiceI;
import ma.youcoude.batiCuisine.utils.EstimateGenerator;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class FetchingProjectDetailsProcess {
    private final Scanner sc;
    private final ProjectServiceI projectService;


    public FetchingProjectDetailsProcess() {
        sc = new Scanner(System.in);
        projectService = new ProjectService();
    }

    public void handleFetchingProjectDetailsProcess() {
        System.out.println("Enter The name of the projects you want to inspect ! ");
        String projectName = sc.nextLine();
        Project existingProject = projectService.getProjectByName(projectName);
        if (existingProject != null) {
            List<Component> allComponents = projectService.getAllComponentsOfProject(existingProject.getProjectId());
            existingProject.setComponents(allComponents);
            EstimateGenerator eg = new EstimateGenerator();
            eg.generateEstimate(existingProject);
        }
        else{
            System.out.println("The project you want to inspect is not found ! TRY AGAIN LATER !");
        }
    }
}
