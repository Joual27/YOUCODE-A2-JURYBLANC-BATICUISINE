package ma.youcoude.batiCuisine.component.workforce;


import ma.youcoude.batiCuisine.component.Component;
import ma.youcoude.batiCuisine.component.material.Material;
import ma.youcoude.batiCuisine.component.workforce.interfaces.WorkForceServiceI;

import java.util.List;
import java.util.stream.Collectors;

public class WorkForceService implements WorkForceServiceI {
    @Override
    public List<Workforce> filterWorkForceOnly(List<Component> components){
            return components.stream()
                .filter(w -> w.getType().equals("Workforce"))
                .map(w -> (Workforce) w)
                .collect(Collectors.toList());
    }


    @Override
    public double calculateSpecificWorkForceCost(Workforce workforce){
        return workforce.getWorkHours() * workforce.getHourlyRate() * workforce.getWorkerProductivityCoefficient();
    }

    @Override
    public double calculateOverallWorkForceCost(List<Workforce> workforces){
         double sum = 0;
         for (Workforce workforce : workforces) {
             sum += calculateSpecificWorkForceCost(workforce);
         }
         return sum;
    }
    @Override
    public double calculateFinalPriceWithVAT(double overallPrice , double VAT){
         return overallPrice + (VAT * overallPrice/100);
    }
}
