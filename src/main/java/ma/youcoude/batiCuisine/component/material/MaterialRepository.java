package ma.youcoude.batiCuisine.component.material;

import ma.youcoude.batiCuisine.component.Component;
import ma.youcoude.batiCuisine.component.material.interfaces.MaterialRepositoryI;
import ma.youcoude.batiCuisine.database.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Component> findAllMaterialsOfProject(String projectId){
        List<Component> components = new ArrayList<>();
        String query = "SELECT * FROM materials WHERE projectId = ?";
        try(PreparedStatement stmt = cnx.prepareStatement(query)){
            stmt.setString(1,projectId);
            try(ResultSet rs = stmt.executeQuery()){
                while(rs.next()){
                    Material material = new Material();
                    material.setComponentId(rs.getString("componentId"));
                    material.setComponentName(rs.getString("componentName"));
                    material.setVAT(rs.getDouble("vat"));
                    material.setQuantity(rs.getInt("quantity"));
                    material.setPricePerUnit(rs.getDouble("pricePerUnit"));
                    material.setTransportationCost(rs.getDouble("transportationCost"));
                    material.setQualityCoefficient(rs.getDouble("qualityCoefficient"));
                    components.add((Component) material);
                }
            }
            return components;
        }
        catch (SQLException e){
            e.printStackTrace();
            return components;
        }
    }
}
