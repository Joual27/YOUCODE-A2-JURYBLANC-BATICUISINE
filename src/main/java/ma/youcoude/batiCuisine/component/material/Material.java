package ma.youcoude.batiCuisine.component.material;

import ma.youcoude.batiCuisine.component.Component;

public class Material extends Component {
    private double pricePerUnit;
    private int quantity;
    private double transportationCost;
    private double qualityCoefficient;

    public Material(){}

    public double getPricePerUnit() {
        return pricePerUnit;
    }
    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getTransportationCost() {
        return transportationCost;
    }
    public void setTransportationCost(double transportationCost) {
        this.transportationCost = transportationCost;
    }

    public double getQualityCoefficient() {
        return qualityCoefficient;
    }
    public void setQualityCoefficient(double qualityCoefficient) {
        this.qualityCoefficient = qualityCoefficient;
    }

}
