package ma.youcoude.batiCuisine.component.material;

import ma.youcoude.batiCuisine.component.material.interfaces.MaterialRepositoryI;
import ma.youcoude.batiCuisine.database.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MaterialRepository implements MaterialRepositoryI {
    private Connection cnx;
    public MaterialRepository() {
        try{
            cnx = DbConnection.getInstance().getConnection();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void save(Material material){
        String query = "INSERT INTO materials VALUES (? , ? , ? , ? , ? , ? , ? , ?)";
        try(PreparedStatement stmt = cnx.prepareStatement(query)){
            stmt.setString(1,material.getComponentId());
            stmt.setString(2,material.getComponentName());
            stmt.setDouble(3,material.getVAT());
            stmt.setString(4,material.getProject().getProjectId());
            stmt.setDouble(5,material.getPricePerUnit());
            stmt.setInt(6,material.getQuantity());
            stmt.setDouble(7,material.getTransportationCost());
            stmt.setDouble(8,material.getQualityCoefficient());
            stmt.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
