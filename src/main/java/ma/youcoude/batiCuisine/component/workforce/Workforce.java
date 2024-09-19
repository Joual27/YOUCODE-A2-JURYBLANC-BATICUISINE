package ma.youcoude.batiCuisine.component.workforce;

import ma.youcoude.batiCuisine.component.Component;

public class Workforce extends Component {
    private double hourlyRate;
    private int workHours;
    private double workerProductivityCoefficient;

    public Workforce(){}

    public double getHourlyRate() {
        return hourlyRate;
    }
    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public int getWorkHours() {
        return workHours;
    }
    public void setWorkHours(int workHours) {
        this.workHours = workHours;
    }
    public double getWorkerProductivityCoefficient() {
        return workerProductivityCoefficient;
    }
    public void setWorkerProductivityCoefficient(double workerProductivityCoefficient) {
        this.workerProductivityCoefficient = workerProductivityCoefficient;
    }

    @Override
    public String getType(){
        return "Workforce";
    }
}
