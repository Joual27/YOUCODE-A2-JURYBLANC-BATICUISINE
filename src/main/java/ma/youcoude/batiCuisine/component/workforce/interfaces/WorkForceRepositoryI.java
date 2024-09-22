package ma.youcoude.batiCuisine.component.workforce.interfaces;

import ma.youcoude.batiCuisine.component.Component;
import ma.youcoude.batiCuisine.component.workforce.Workforce;

import java.util.List;

public interface WorkForceRepositoryI {
    public void save(Workforce workforce);

    public List<Component> findAllWorkforcesOfProject(String projectId);
}
