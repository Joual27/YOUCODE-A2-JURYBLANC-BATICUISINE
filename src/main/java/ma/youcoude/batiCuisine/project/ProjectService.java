package ma.youcoude.batiCuisine.project;

import ma.youcoude.batiCuisine.component.Component;
import ma.youcoude.batiCuisine.component.material.Material;
import ma.youcoude.batiCuisine.component.material.MaterialRepository;
import ma.youcoude.batiCuisine.component.workforce.WorkForceRepository;
import ma.youcoude.batiCuisine.component.workforce.Workforce;
import ma.youcoude.batiCuisine.component.workforce.interfaces.WorkForceRepositoryI;
import ma.youcoude.batiCuisine.project.interfaces.ProjectRepositoryI;
import ma.youcoude.batiCuisine.project.interfaces.ProjectServiceI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProjectService implements ProjectServiceI {

    private final ProjectRepositoryI projectRepository;
    private final MaterialRepository materialRepository;
    private final WorkForceRepositoryI workForceRepository;

    public ProjectService() {
        this.projectRepository = new ProjectRepository();
        this.materialRepository = new MaterialRepository();
        this.workForceRepository = new WorkForceRepository();
    }

    @Override
    public Project saveProject(Project project){
        String projectId = UUID.randomUUID().toString();
        project.setProjectId(projectId);
        projectRepository.saveProject(project);
        for(Component c : project.getComponents()){
            Project p = new Project();
            p.setProjectId(projectId);
            c.setProject(p);
            if (c instanceof Material material){
                material.setComponentId(UUID.randomUUID().toString());
                materialRepository.save(material);
            }
            else{
                Workforce workforce = (Workforce) c;
                workforce.setComponentId(UUID.randomUUID().toString());
                workForceRepository.save(workforce);
            }
        }
        return project;
    }

    @Override
    public List<Component> getAllComponentsOfProject(String projectId){
        List<Component> allComponents = new ArrayList<>();
        List<Component> materials = materialRepository.findAllMaterialsOfProject(projectId);
        List<Component> workforces = workForceRepository.findAllWorkforcesOfProject(projectId);
        allComponents.addAll(materials);
        allComponents.addAll(workforces);
        return allComponents;
    }

    @Override
    public List<Project> getAllProjects(){
        List<Project> allProjects = projectRepository.getAllProjects();
        for(Project p : allProjects){
            List<Component> components = getAllComponentsOfProject(p.getProjectId());
            p.setComponents(components);
        }
        return allProjects;
    }


}
