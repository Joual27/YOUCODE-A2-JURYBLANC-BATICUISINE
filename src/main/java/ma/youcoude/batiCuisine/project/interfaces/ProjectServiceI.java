package ma.youcoude.batiCuisine.project.interfaces;

import ma.youcoude.batiCuisine.component.Component;
import ma.youcoude.batiCuisine.estimate.Estimate;
import ma.youcoude.batiCuisine.project.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectServiceI {

    public Project saveProject(Project project);

    public List<Component> getAllComponentsOfProject(String projectId);

    public List<Project> getAllProjects();

    public Project getProjectByName(String projectName);

    public Estimate getEstimateOfProject(String projectId);
}
