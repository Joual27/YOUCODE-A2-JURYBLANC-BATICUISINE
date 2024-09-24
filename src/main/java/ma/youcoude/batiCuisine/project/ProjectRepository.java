package ma.youcoude.batiCuisine.project;

import ma.youcoude.batiCuisine.customer.Customer;
import ma.youcoude.batiCuisine.customer.CustomerRepository;
import ma.youcoude.batiCuisine.customer.interfaces.CustomerRepositoryI;
import ma.youcoude.batiCuisine.database.DbConnection;
import ma.youcoude.batiCuisine.enums.ProjectStatus;
import ma.youcoude.batiCuisine.estimate.Estimate;
import ma.youcoude.batiCuisine.project.interfaces.ProjectRepositoryI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjectRepository implements ProjectRepositoryI {

    private Connection cnx;
    public ProjectRepository() {
        try{
            cnx = DbConnection.getInstance().getConnection();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void saveProject(Project project){
        String query = "INSERT INTO projects VALUES (? , ? , ? , ? , ?)";
        try(PreparedStatement stmt = cnx.prepareStatement(query)){
            stmt.setString(1 , project.getProjectId());
            stmt.setString(2 , project.getProjectName());
            stmt.setDouble(3 , project.getProfitMargin());
            stmt.setObject(4 , project.getProjectStatus().name() , java.sql.Types.OTHER);
            stmt.setString(5 , project.getCustomer().getFullName());
            stmt.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    @Override
    public List<Project> getAllProjects(){
        String query = "SELECT * FROM projects";
        List<Project> projects = new ArrayList<>();
        try(PreparedStatement stmt = cnx.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Project project = new Project();
                project.setProjectId(rs.getString("projectid"));
                project.setProjectName(rs.getString("projectname"));
                project.setProfitMargin(rs.getDouble("profitmargin"));
                project.setProjectStatus(ProjectStatus.valueOf(rs.getString("projectstatus")));
                CustomerRepositoryI cr = new CustomerRepository();
                Optional<Customer> c = cr.findCustomerByFullName(rs.getString("fullname"));
                project.setCustomer(c.get());
                projects.add(project);
            }
            return projects;
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Optional<Project> getProjectByName(String projectName){
        String query = "SELECT * FROM projects JOIN customers ON projects.fullname = customers.fullname WHERE projectname = ?";
        try(PreparedStatement stmt = cnx.prepareStatement(query)){
            stmt.setString(1 , projectName);
            ResultSet rs = stmt.executeQuery();
            Project project = new Project();
            if(rs.next()){
                project.setProjectId(rs.getString("projectid"));
                project.setProjectName(rs.getString("projectname"));
                project.setProfitMargin(rs.getDouble("profitmargin"));
                Customer customer = new Customer();
                customer.setFullName(rs.getString("fullname"));
                customer.setAddress(rs.getString("adress"));
                customer.setProfessional(rs.getBoolean("isprofessional"));
                project.setCustomer(customer);
                return Optional.of(project);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<Estimate> getEstimateOfProject(String projectId){
        String query = "SELECT * FROM estimates WHERE projectId = ?";
        try(PreparedStatement stmt = cnx.prepareStatement(query)){
            stmt.setString(1 , projectId);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                Estimate estimate = new Estimate();
                estimate.setOverallEstimatedPrice(rs.getDouble("estimatedoverallprice"));
                estimate.setIssuedAt(rs.getTimestamp("issuedat").toLocalDateTime());
                estimate.setValidityDate(rs.getDate("validitydate").toLocalDate());
                estimate.setAccepted(rs.getBoolean("accepted"));
                return  Optional.of(estimate);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }

}
