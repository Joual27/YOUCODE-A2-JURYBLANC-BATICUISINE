package ma.youcoude.batiCuisine.project.interfaces;

import ma.youcoude.batiCuisine.component.Component;
import ma.youcoude.batiCuisine.project.Project;

import java.util.List;

public interface ProjectServiceI {

    public Project saveProject(Project project);

    public List<Component> getAllComponentsOfProject(String projectId);

    public List<Project> getAllProjects();
}
