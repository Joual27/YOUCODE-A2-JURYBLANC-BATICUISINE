package ma.youcoude.batiCuisine.component.material.interfaces;

import ma.youcoude.batiCuisine.component.Component;
import ma.youcoude.batiCuisine.component.material.Material;

import java.util.List;

public interface MaterialServiceI {
    public List<Material> filterMaterialsOnly(List<Component> components);
    public double calculateSpecificMaterialCost(Material material);
    public double calculateOverallMaterialCost(List<Material> materials);
    public double calculateFinalPriceWithVAT(double overallPrice , double VAT);
}
