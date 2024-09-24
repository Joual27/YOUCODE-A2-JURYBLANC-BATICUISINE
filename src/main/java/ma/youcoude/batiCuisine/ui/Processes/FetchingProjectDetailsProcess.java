package ma.youcoude.batiCuisine.ui.Processes;

import ma.youcoude.batiCuisine.component.Component;
import ma.youcoude.batiCuisine.estimate.Estimate;
import ma.youcoude.batiCuisine.estimate.EstimateService;
import ma.youcoude.batiCuisine.estimate.interfaces.EstimateServiceI;
import ma.youcoude.batiCuisine.project.Project;
import ma.youcoude.batiCuisine.project.ProjectService;
import ma.youcoude.batiCuisine.project.interfaces.ProjectServiceI;
import ma.youcoude.batiCuisine.utils.DatesValidator;
import ma.youcoude.batiCuisine.utils.EstimateGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class FetchingProjectDetailsProcess {
    private final Scanner sc;
    private final ProjectServiceI projectService;
    private final EstimateServiceI estimateService;
    private final EstimateGenerator estimateGenerator;

    public FetchingProjectDetailsProcess() {
        sc = new Scanner(System.in);
        projectService = new ProjectService();
        estimateService = new EstimateService();
        estimateGenerator = new EstimateGenerator();
    }

    public void handleFetchingProjectDetailsProcess() {
        System.out.println("Enter The name of the projects you want to inspect ! ");
        String projectName = sc.nextLine();
        Project existingProject = projectService.getProjectByName(projectName);
        if (existingProject != null) {
            List<Component> allComponents = projectService.getAllComponentsOfProject(existingProject.getProjectId());
            existingProject.setComponents(allComponents);
            Estimate e = new Estimate();
            if (projectService.getEstimateOfProject(existingProject.getProjectId()) == null) {
                System.out.println("This Project has no estimate yet ! do you want to generate one ? (Y/N)");
                String answer = sc.nextLine();
                if (answer.equalsIgnoreCase("y")) {
                    LocalDateTime issueDate ;
                    System.out.println("PLease provide the estimate issue Date");
                    while (true){
                        int days = DatesValidator.handleDays();
                        int months = DatesValidator.handleMonths();
                        int year = DatesValidator.handleYear();

                        LocalDate date = LocalDate.of(year, months, days);
                        if (DatesValidator.validateIssueDate(date)){
                            issueDate = date.atStartOfDay();
                            break;
                        }
                        else{
                            System.out.println("Issue Date can't be before Current date");
                        }
                    }
                    LocalDate validityDate;
                    System.out.println("PLease provide the estimate Validity Date");

                    while (true){
                        int days = DatesValidator.handleDays();
                        int months = DatesValidator.handleMonths();
                        int year = DatesValidator.handleYear();

                        LocalDate date = LocalDate.of(year, months, days);
                        if (DatesValidator.validateValidityDate(date, issueDate)){
                            validityDate = date;
                            break;
                        }
                        else {
                            System.out.println("Validity Date can't be before Issue Date");
                        }
                    }
                    boolean isEstimateAccepted;
                    while (true){
                        System.out.println("Was This Estimate ALready accepted by " + existingProject.getCustomer().getFullName() + " ? (Y/N)");
                        String choice = sc.nextLine();
                        if (choice.equalsIgnoreCase("y")){
                            isEstimateAccepted = true;
                            break;
                        }
                        else if (choice.equalsIgnoreCase("N")){
                            isEstimateAccepted = false;
                            break;
                        }
                        else {
                            System.out.println("Invalid option please enter either Y or N !");
                        }
                    }
                    e.setIssuedAt(issueDate);
                    e.setValidityDate(validityDate);
                    e.setAccepted(isEstimateAccepted);
                    e.setOverallEstimatedPrice(estimateService.calculateProjectFinalPrice(existingProject));
                    e.setProject(existingProject);
                    Estimate createdEstimate = estimateService.save(e);
                    existingProject.setEstimate(createdEstimate);
                    estimateGenerator.generateEstimate(existingProject);
                }
                else {
                    estimateGenerator.generateEstimate(existingProject);
                }
            }
            else{
                Estimate estimate = projectService.getEstimateOfProject(existingProject.getProjectId());
                existingProject.setEstimate(estimate);
                estimateGenerator.generateEstimate(existingProject);
            }
        }
        else{
            System.out.println("The project you want to inspect is not found ! TRY AGAIN LATER !");
        }
    }
}
