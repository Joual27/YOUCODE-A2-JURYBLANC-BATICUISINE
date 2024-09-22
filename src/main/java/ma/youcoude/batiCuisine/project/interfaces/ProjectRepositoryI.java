package ma.youcoude.batiCuisine.project.interfaces;

import ma.youcoude.batiCuisine.project.Project;

import java.util.List;

public interface ProjectRepositoryI {

    public void saveProject(Project project);
    public List<Project> getAllProjects();
}
