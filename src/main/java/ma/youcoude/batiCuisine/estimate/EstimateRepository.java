package ma.youcoude.batiCuisine.estimate;

import ma.youcoude.batiCuisine.database.DbConnection;
import ma.youcoude.batiCuisine.estimate.interfaces.EstimateRepositoryI;

import java.sql.*;

public class EstimateRepository implements EstimateRepositoryI {

    private Connection cnx;
    public EstimateRepository() {
        try{
            cnx = DbConnection.getInstance().getConnection();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void save(Estimate estimate){
        String query = "INSERT INTO estimates VALUES (?,?,?,?,?,?)";
        try(PreparedStatement stmt = cnx.prepareStatement(query)){
            stmt.setString(1 , estimate.getEstimateId());
            stmt.setDouble(2,estimate.getOverallEstimatedPrice());
            stmt.setTimestamp(3, Timestamp.valueOf(estimate.getIssuedAt()));
            stmt.setDate(4, Date.valueOf(estimate.getValidityDate()));
            stmt.setBoolean(5 , false);
            stmt.setString(6,estimate.getProject().getProjectId());
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
