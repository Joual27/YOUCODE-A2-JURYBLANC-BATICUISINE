package ma.youcoude.batiCuisine.component.workforce;

import ma.youcoude.batiCuisine.component.Component;
import ma.youcoude.batiCuisine.component.workforce.interfaces.WorkForceRepositoryI;
import ma.youcoude.batiCuisine.database.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    @Override
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

    @Override
    public List<Component> findAllWorkforcesOfProject(String projectId){
        List<Component> components = new ArrayList<>();
        String query = "SELECT * FROM workforce WHERE projectId = ? ";
        try(PreparedStatement stmt = cnx.prepareStatement(query)){
            stmt.setString(1,projectId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Workforce workforce = new Workforce();
                workforce.setComponentId(rs.getString("componentId"));
                workforce.setComponentName(rs.getString("componentName"));
                workforce.setVAT(rs.getDouble("vat"));
                workforce.setHourlyRate(rs.getDouble("hourlyRate"));
                workforce.setWorkerProductivityCoefficient(rs.getDouble("workerProductivityCoefficient"));
                workforce.setWorkHours(rs.getInt("workHours"));
                components.add((Component) workforce);
            }
            return components;
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }


}
