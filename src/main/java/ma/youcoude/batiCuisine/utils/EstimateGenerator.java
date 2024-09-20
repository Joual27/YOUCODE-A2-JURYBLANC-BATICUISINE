package ma.youcoude.batiCuisine.utils;

import ma.youcoude.batiCuisine.component.Component;
import ma.youcoude.batiCuisine.component.material.Material;
import ma.youcoude.batiCuisine.component.material.MaterialService;
import ma.youcoude.batiCuisine.component.material.interfaces.MaterialServiceI;
import ma.youcoude.batiCuisine.component.workforce.WorkForceService;
import ma.youcoude.batiCuisine.component.workforce.Workforce;
import ma.youcoude.batiCuisine.component.workforce.interfaces.WorkForceServiceI;
import ma.youcoude.batiCuisine.project.Project;

import java.util.List;

public class EstimateGenerator {

    private final MaterialServiceI materialService;
    private final WorkForceServiceI workForceService;

    public EstimateGenerator() {
        this.materialService = new MaterialService();
        this.workForceService = new WorkForceService();
    }

    public void generateEstimate(Project project) {
        System.out.println("╔══════════════════════════════════════════════════════════════════════════════╗");
        System.out.printf("║ %-74s ║\n", "Project: " + project.getProjectName());
        System.out.printf("║ %-74s ║\n", "Client: " + project.getCustomer().getFullName());
        System.out.printf("║ %-74s ║\n", "Address: " + project.getCustomer().getAddress());
        System.out.println("╠══════════════════════════════════════════════════════════════════════════════╣");


        List<Material> materials = materialService.filterMaterialsOnly(project.getComponents());
        System.out.println("║                              Materials                                       ║");
        System.out.println("╠══════════════════════════════════════════════════════════════════════════════╣");
        System.out.printf("║ %-20s %-10s %-12s %-12s %-8s %-10s ║\n", "Material", "Quantity", "Unit Price", "Total Cost", "Quality", "Transport");
        System.out.println("╠══════════════════════════════════════════════════════════════════════════════╣");

        double totalMaterialCost = 0;
        for (Material material : materials) {
            System.out.printf("║ %-20s %-12d %-12.2f %-12.2f %-8.1f %-10.2f ║\n",
                    material.getComponentName(),
                    material.getQuantity(),
                    material.getPricePerUnit(),
                    materialService.calculateSpecificMaterialCost(material),
                    material.getQualityCoefficient(),
                    material.getTransportationCost()
            );
            totalMaterialCost += materialService.calculateSpecificMaterialCost(material);
        }

        double materialCostWithVAT = materialService.calculateFinalPriceWithVAT(totalMaterialCost, project.getComponents().getFirst().getVAT());
        System.out.println("╠══════════════════════════════════════════════════════════════════════════════╣");
        System.out.printf("║ %-74s ║\n", "Total material cost before VAT: " + totalMaterialCost + " $");
        System.out.printf("║ %-74s ║\n", "Total material cost with VAT (20%): " + materialCostWithVAT + " $");
        System.out.println("╚══════════════════════════════════════════════════════════════════════════════╝");


        List<Workforce> workforces = workForceService.filterWorkForceOnly(project.getComponents());
        System.out.println("╔══════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                              Workforce                                       ║");
        System.out.println("╠══════════════════════════════════════════════════════════════════════════════╣");
        System.out.printf("║ %-20s %-10s %-12s %-12s %-8s ║\n", "Worker", "Hourly Rate", "Hours Worked", "Total Cost", "Productivity");
        System.out.println("╠══════════════════════════════════════════════════════════════════════════════╣");

        double totalWorkforceCost = 0;
        for (Workforce workforce : workforces) {
            System.out.printf("║ %-20s %-10.2f %-12d %-12.2f %-8.1f ║\n",
                    workforce.getComponentName(),
                    workforce.getHourlyRate(),
                    workforce.getWorkHours(),
                    workForceService.calculateSpecificWorkForceCost(workforce),
                    workforce.getWorkerProductivityCoefficient()
            );
            totalWorkforceCost += workForceService.calculateSpecificWorkForceCost(workforce);
        }

        double workforceCostWithVAT = workForceService.calculateFinalPriceWithVAT(totalWorkforceCost, project.getComponents().getFirst().getVAT());
        System.out.println("╠══════════════════════════════════════════════════════════════════════════════╣");
        System.out.printf("║ %-74s ║\n", "Total workforce cost before VAT: " + totalWorkforceCost + "$");
        System.out.printf("║ %-74s ║\n", "Total workforce cost with VAT (20%): " + workforceCostWithVAT + " $");
        System.out.println("╚══════════════════════════════════════════════════════════════════════════════╝");

        double finalCost = totalMaterialCost + totalWorkforceCost;
        System.out.println("╔══════════════════════════════════════════════════════════════════════════════╗");
        System.out.printf("║ %-74s ║\n", "Total project cost before profit margin: " + finalCost + " $");
        double profitMargin = project.getProfitMargin();
        double finalCostWithMargin = finalCost + (finalCost * profitMargin / 100);
        System.out.printf("║ %-74s ║\n", "Profit margin (" + (profitMargin) + "%): " + (finalCost * profitMargin / 100) + " $");
        System.out.printf("║ %-74s ║\n", "Final total cost: " + finalCostWithMargin + " $");
        System.out.println("╚══════════════════════════════════════════════════════════════════════════════╝");
    }

}
