package ma.youcoude.batiCuisine.ui.Processes;

import ma.youcoude.batiCuisine.component.Component;
import ma.youcoude.batiCuisine.component.material.Material;
import ma.youcoude.batiCuisine.component.material.MaterialService;
import ma.youcoude.batiCuisine.component.material.interfaces.MaterialServiceI;
import ma.youcoude.batiCuisine.component.workforce.WorkForceService;
import ma.youcoude.batiCuisine.component.workforce.Workforce;
import ma.youcoude.batiCuisine.component.workforce.interfaces.WorkForceServiceI;
import ma.youcoude.batiCuisine.project.Project;
import ma.youcoude.batiCuisine.project.ProjectService;
import ma.youcoude.batiCuisine.project.interfaces.ProjectServiceI;

import java.util.List;

public class FetchingAllProjectsProcess {

    private final ProjectServiceI projectService;
    private final MaterialServiceI materialService;
    private final WorkForceServiceI workForceService;

    public FetchingAllProjectsProcess() {
        projectService = new ProjectService();
        materialService = new MaterialService();
        workForceService = new WorkForceService();
    }

    public void handleFetchingAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        for (Project project : projects) {
            System.out.println("Project ID: " + project.getProjectId());
            System.out.println("Project Name: " + project.getProjectName());
            System.out.println("Project Address: " + project.getCustomer().getAddress());

            System.out.println("--------------------------------------------------");

            List<Component> components = project.getComponents();
            System.out.println("Components:");
            for (Component component : components) {
                if (component instanceof Material material) {
                    System.out.println(" - Component Name: " + material.getComponentName());
                    System.out.println("   Type: Material");
                    System.out.println("   Quantity: " + material.getQuantity());
                    System.out.println("   Unit Price: " + material.getPricePerUnit() + " $");
                    System.out.println("   Transportation Cost: " + material.getTransportationCost() + " $");
                    System.out.println("   Total Price: " + materialService.calculateSpecificMaterialCost(material) + " $");
                    System.out.println("   ------------------------------------------");
                }
                else if (component instanceof Workforce workforce){
                    System.out.println(" - Component Name: " + workforce.getComponentName());
                    System.out.println("   Type: Workforce");
                    System.out.println("   Hourly Rate: " + workforce.getHourlyRate());
                    System.out.println("   Work Hours: " + workforce.getWorkHours());
                    System.out.println("   Total Price: " + workForceService.calculateSpecificWorkForceCost(workforce) + " $");
                    System.out.println("   ------------------------------------------");
                }
            }

            System.out.println("===================================================");
        }
    }
}
