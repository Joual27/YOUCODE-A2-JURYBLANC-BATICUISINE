package ma.youcoude.batiCuisine.component.workforce;

import ma.youcoude.batiCuisine.component.workforce.interfaces.WorkForceRepositoryI;
import ma.youcoude.batiCuisine.database.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WorkForceRepository implements WorkForceRepositoryI {
    private Connection cnx;
    public WorkForceRepository() {
        try{
            cnx = DbConnection.getInstance().getConnection();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void save(Workforce workforce){
        String query = "INSERT INTO workforce VALUES (? , ? , ? , ? , ? , ? , ? )";
        try(PreparedStatement stmt = cnx.prepareStatement(query)){
            stmt.setString(1,workforce.getComponentId());
            stmt.setString(2,workforce.getComponentName());
            stmt.setDouble(3,workforce.getVAT());
            stmt.setString(4,workforce.getProject().getProjectId());
            stmt.setDouble(5,workforce.getHourlyRate());
            stmt.setDouble(6,workforce.getWorkerProductivityCoefficient());
            stmt.setInt(7,workforce.getWorkHours());
            stmt.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
