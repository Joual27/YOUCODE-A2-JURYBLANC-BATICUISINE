package ma.youcoude.batiCuisine.project.interfaces;

import ma.youcoude.batiCuisine.estimate.Estimate;
import ma.youcoude.batiCuisine.project.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectRepositoryI {

    public void saveProject(Project project);
    public List<Project> getAllProjects();
    public Optional<Project> getProjectByName(String projectName);
    public Optional<Estimate> getEstimateOfProject(String projectId);

}
