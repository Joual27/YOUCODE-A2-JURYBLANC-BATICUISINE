package ma.youcoude.batiCuisine.component.workforce.interfaces;

import ma.youcoude.batiCuisine.component.Component;
import ma.youcoude.batiCuisine.component.material.Material;
import ma.youcoude.batiCuisine.component.workforce.Workforce;

import java.util.List;
import java.util.stream.Collectors;

public interface WorkForceServiceI {
    public List<Workforce> filterWorkForceOnly(List<Component> components);
    public double calculateSpecificWorkForceCost(Workforce workforce);
    public double calculateOverallWorkForceCost(List<Workforce> workforces);
    public double calculateFinalPriceWithVAT(double overallPrice , double VAT);
}
