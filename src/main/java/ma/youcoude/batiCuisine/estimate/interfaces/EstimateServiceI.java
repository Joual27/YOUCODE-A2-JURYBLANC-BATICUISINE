package ma.youcoude.batiCuisine.estimate.interfaces;

import ma.youcoude.batiCuisine.estimate.Estimate;
import ma.youcoude.batiCuisine.project.Project;

public interface EstimateServiceI {
    public double calculateProjectFinalPrice(Project project);
    public Estimate save(Estimate estimate);
}
