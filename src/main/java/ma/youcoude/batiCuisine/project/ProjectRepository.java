package ma.youcoude.batiCuisine.project;

import ma.youcoude.batiCuisine.database.DbConnection;
import ma.youcoude.batiCuisine.project.interfaces.ProjectRepositoryI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
