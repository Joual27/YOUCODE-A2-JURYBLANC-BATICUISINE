package ma.youcoude.batiCuisine.component.material;

import ma.youcoude.batiCuisine.component.Component;
import ma.youcoude.batiCuisine.component.material.interfaces.MaterialServiceI;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MaterialService implements MaterialServiceI {
    @Override
    public List<Material> filterMaterialsOnly(List<Component> components){
        return components.stream()
                .filter(c -> c.getType().equals("Material"))
                .map(c -> (Material) c)
                .collect(Collectors.toList());
    }

    @Override
    public double calculateSpecificMaterialCost(Material material){
        return (material.getPricePerUnit() * material.getQuantity() * material.getQualityCoefficient()) + material.getTransportationCost();
    }

    @Override
    public double calculateOverallMaterialCost(List<Material> materials){
        double sum = 0;
        for (Material material : materials) {
            sum += calculateSpecificMaterialCost(material);
        }
        return sum;
    }

    @Override
    public double calculateFinalPriceWithVAT(double overallPrice , double VAT){
        return overallPrice + (overallPrice * VAT/100);
    }


}
