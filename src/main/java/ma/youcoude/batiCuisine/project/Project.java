package ma.youcoude.batiCuisine.project;

import ma.youcoude.batiCuisine.component.Component;
import ma.youcoude.batiCuisine.customer.Customer;
import ma.youcoude.batiCuisine.enums.ProjectStatus;
import ma.youcoude.batiCuisine.estimate.Estimate;

import java.util.List;

public class Project {
    private String projectId;
    private String projectName;
    private double overallPrice;
    private double profitMargin;
    private ProjectStatus projectStatus;
    private Customer customer;
    private Estimate estimate;
    private List<Component> components;

    public Project(){}

    public String getProjectId() {
        return projectId;
    }
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
    public String getProjectName() {
        return projectName;
    }
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    public double getOverallPrice() {
        return overallPrice;
    }
    public void setOverallPrice(double overallPrice) {
        this.overallPrice = overallPrice;
    }
    public double getProfitMargin() {
        return profitMargin;
    }
    public void setProfitMargin(double profitMargin) {
        this.profitMargin = profitMargin;
    }
    public ProjectStatus getProjectStatus() {
        return projectStatus;
    }
    public void setProjectStatus(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public Estimate getEstimate() {
        return estimate;
    }
    public void setEstimate(Estimate estimate) {
        this.estimate = estimate;
    }
    public List<Component> getComponents() {
        return components;
    }
    public void setComponents(List<Component> components) {
        this.components = components;
    }

}
