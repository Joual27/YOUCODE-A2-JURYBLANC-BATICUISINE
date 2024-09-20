package ma.youcoude.batiCuisine.estimate;

import ma.youcoude.batiCuisine.component.material.Material;
import ma.youcoude.batiCuisine.component.material.MaterialService;
import ma.youcoude.batiCuisine.component.material.interfaces.MaterialServiceI;
import ma.youcoude.batiCuisine.component.workforce.WorkForceService;
import ma.youcoude.batiCuisine.component.workforce.Workforce;
import ma.youcoude.batiCuisine.component.workforce.interfaces.WorkForceServiceI;
import ma.youcoude.batiCuisine.estimate.interfaces.EstimateServiceI;
import ma.youcoude.batiCuisine.project.Project;

import java.util.List;

public class EstimateService implements EstimateServiceI {

    private MaterialServiceI materialService;
    private WorkForceServiceI workForceService;

    public EstimateService() {
        materialService = new MaterialService();
        workForceService = new WorkForceService();
    }

    public double CalculateProjectFinalPrice(Project project){
        List<Material> materials = materialService.filterMaterialsOnly(project.getComponents());
        List<Workforce> workforces = workForceService.filterWorkForceOnly(project.getComponents());

        double VAT = project.getComponents().getFirst().getVAT();

        double materialsPriceBeforeVAT = materialService.calculateOverallMaterialCost(materials);
        double materialsPriceAfterVAT = materialService.calculateFinalPriceWithVAT(materialsPriceBeforeVAT , VAT);

        double workforcePriceBeforeVAT = workForceService.calculateOverallWorkForceCost(workforces);
        double workforcePriceAfterVAT = workForceService.calculateFinalPriceWithVAT(workforcePriceBeforeVAT , VAT);

        double priceBeforeProfitMargin = materialsPriceAfterVAT + workforcePriceAfterVAT;
        return priceBeforeProfitMargin + (priceBeforeProfitMargin * project.getProfitMargin() / 100);
    }
}
