package ma.youcoude.batiCuisine.estimate;

import ma.youcoude.batiCuisine.component.material.Material;
import ma.youcoude.batiCuisine.component.material.MaterialService;
import ma.youcoude.batiCuisine.component.material.interfaces.MaterialServiceI;
import ma.youcoude.batiCuisine.component.workforce.WorkForceService;
import ma.youcoude.batiCuisine.component.workforce.Workforce;
import ma.youcoude.batiCuisine.component.workforce.interfaces.WorkForceServiceI;
import ma.youcoude.batiCuisine.estimate.interfaces.EstimateRepositoryI;
import ma.youcoude.batiCuisine.estimate.interfaces.EstimateServiceI;
import ma.youcoude.batiCuisine.project.Project;

import java.util.List;
import java.util.UUID;

public class EstimateService implements EstimateServiceI {

    private MaterialServiceI materialService;
    private WorkForceServiceI workForceService;
    private EstimateRepositoryI estimateRepository;

    public EstimateService() {
        materialService = new MaterialService();
        workForceService = new WorkForceService();
        estimateRepository = new EstimateRepository();
    }

    @Override
    public double calculateProjectFinalPrice(Project project){
        List<Material> materials = materialService.filterMaterialsOnly(project.getComponents());
        List<Workforce> workforces = workForceService.filterWorkForceOnly(project.getComponents());

        double VAT = project.getComponents().getFirst().getVAT();

        double materialsPriceBeforeVAT = materialService.calculateOverallMaterialCost(materials);
        double materialsPriceAfterVAT = materialService.calculateFinalPriceWithVAT(materialsPriceBeforeVAT , VAT);

        double workforcePriceBeforeVAT = workForceService.calculateOverallWorkForceCost(workforces);
        double workforcePriceAfterVAT = workForceService.calculateFinalPriceWithVAT(workforcePriceBeforeVAT , VAT);

        double priceBeforeProfitMargin = materialsPriceAfterVAT + workforcePriceAfterVAT;
        double priceBeforeDiscount =  priceBeforeProfitMargin + (priceBeforeProfitMargin * project.getProfitMargin() / 100);
        return calculateDiscount(project , priceBeforeDiscount);
    }

    @Override
    public Estimate save(Estimate estimate){
        estimate.setEstimateId(UUID.randomUUID().toString());
        estimateRepository.save(estimate);
        return estimate;
    }

    @Override
    public double calculateDiscount(Project project , double estimatedPrice){
        if (project.getCustomer().isProfessional()){
            if (estimatedPrice > 5000 && estimatedPrice < 10000){
                return estimatedPrice - (estimatedPrice * 5 / 100);
            }
            else if (estimatedPrice > 10000){
                return estimatedPrice - (estimatedPrice * 7.5 / 100);
            }
        }
        return estimatedPrice;
    }

}
