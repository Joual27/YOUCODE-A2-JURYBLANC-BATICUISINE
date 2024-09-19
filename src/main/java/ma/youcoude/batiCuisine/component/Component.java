package ma.youcoude.batiCuisine.component;

import ma.youcoude.batiCuisine.project.Project;

public abstract class Component {
    private String componentId;
    private String componentName;
    private double VAT;
    private Project project;

    public Component() {}

    public String getComponentId() {
        return componentId;
    }
    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }
    public String getComponentName() {
        return componentName;
    }
    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }
    public double getVAT() {
        return VAT;
    }
    public void setVAT(double VAT) {
        this.VAT = VAT;
    }
    public Project getProject() {
        return project;
    }
    public void setProject(Project project) {
        this.project = project;
    }


}
