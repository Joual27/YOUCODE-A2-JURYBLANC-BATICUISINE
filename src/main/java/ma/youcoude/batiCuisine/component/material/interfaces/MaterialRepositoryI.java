package ma.youcoude.batiCuisine.component.material.interfaces;

import ma.youcoude.batiCuisine.component.Component;
import ma.youcoude.batiCuisine.component.material.Material;

import java.util.List;

public interface MaterialRepositoryI {
    public void save(Material material);

    public List<Component> findAllMaterialsOfProject(String projectId);
}
