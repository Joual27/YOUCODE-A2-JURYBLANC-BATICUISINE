package ma.youcoude.batiCuisine.ui.Processes;

import ma.youcoude.batiCuisine.enums.ProjectStatus;
import ma.youcoude.batiCuisine.project.Project;
import ma.youcoude.batiCuisine.project.ProjectService;
import ma.youcoude.batiCuisine.project.interfaces.ProjectServiceI;
import ma.youcoude.batiCuisine.exceptions.ProjectNotFoundException;

import java.util.List;
import java.util.Scanner;

public class ProjectSubMenuProcess {
    private final Scanner sc;
    private final ProjectServiceI projectService;

    public ProjectSubMenuProcess() {
        sc = new Scanner(System.in);
        projectService = new ProjectService();
    }

    public void handleFetchingAllProjectsProcess() {
        List<Project> projects = projectService.getAllProjects();
        System.out.println("╔══════════════════════════════════════════════════════════════════════════════╗");
        System.out.printf("║ %-15s │ %-20s │ %-15s │ %-20s ║\n", "Project ID", "Project Name", "Profit Margin", "Project Status");
        System.out.println("╠══════════════════════════════════════════════════════════════════════════════╣");

        for (Project project : projects) {
            String projectId = project.getProjectId();
            String projectName = project.getProjectName();
            String profitMargin = String.format("%.2f", project.getProfitMargin()) + "%";
            ProjectStatus projectStatus = project.getProjectStatus();

            System.out.printf("║ %-15s │ %-20s │ %-15s │ %-20s ║\n", projectId, projectName, profitMargin, projectStatus);
        }
        System.out.println("╚══════════════════════════════════════════════════════════════════════════════╝");
    }

    public void handleUpdatingProjectProcess() {
        handleFetchingAllProjectsProcess();
        Project projectToUpdate = new Project();

        while (true) {
            System.out.println("Please provide the name of the project you want to update:");
            String projectName = sc.nextLine();
            try {
                projectToUpdate = projectService.getProjectByName(projectName);
                if (projectToUpdate == null) {
                    throw new ProjectNotFoundException("No project found with name: " + projectName);
                }
                System.out.println("Project Found!");
                break;
            } catch (ProjectNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Current Profit Margin: " + projectToUpdate.getProfitMargin() + "%");
        System.out.println("Enter a new value or Press enter to keep the same value:");
        String profitMarginValue = sc.nextLine();
        if (!profitMarginValue.trim().isEmpty()) {
            try {
                double newProfitMargin = Double.parseDouble(profitMarginValue);
                projectToUpdate.setProfitMargin(newProfitMargin);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid percentage.");
            }
        }

        while (true) {
            ProjectStatus currentStatus = projectToUpdate.getProjectStatus();
            System.out.println("Current Project Status: " + currentStatus);
            System.out.println("Enter a new value (ONGOING, ENDED, CANCELLED) or Press enter to keep the same value:");
            String userInput = sc.nextLine();
            if (!userInput.trim().isEmpty()) {
                if (userInput.equalsIgnoreCase("ONGOING") || userInput.equalsIgnoreCase("Completed") || userInput.equalsIgnoreCase("Cancelled")) {
                    ProjectStatus statusValue = ProjectStatus.valueOf(userInput.toUpperCase());
                    projectToUpdate.setProjectStatus(statusValue);
                    break;
                } else {
                    System.out.println("Invalid status. Please enter a valid status (In Progress, Completed, Cancelled).");
                }
            } else {
                break;
            }
        }

        Project updatedProject = projectService.updateProject(projectToUpdate);
        System.out.println("Project " + updatedProject.getProjectName() + " has been updated successfully!");
    }

    public void handleProjectDeletionProcess() {
        handleFetchingAllProjectsProcess();
        System.out.println("Please provide the name of the project you want to delete:");
        String projectName = sc.nextLine();
        try {
            Project projectToDelete = projectService.getProjectByName(projectName);
            if (projectToDelete == null) {
                throw new ProjectNotFoundException("No project found with name: " + projectName);
            }
            projectService.deleteProject(projectName);
            System.out.println("Project " + projectToDelete.getProjectName() + " has been deleted successfully!");
        } catch (ProjectNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
