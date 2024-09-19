package ma.youcoude.batiCuisine.customer;

import ma.youcoude.batiCuisine.customer.interfaces.CustomerRepositoryI;
import ma.youcoude.batiCuisine.database.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class CustomerRepository implements CustomerRepositoryI {


    private Connection cnx;

    public CustomerRepository() {
        try{
            cnx = DbConnection.getInstance().getConnection();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Customer> findCustomerByFullName(String fullName){
        String query = "SELECT * FROM customers WHERE fullName = ?";
        try (PreparedStatement stmt = cnx.prepareStatement(query)) {
            stmt.setString(1, fullName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Customer customer = new Customer();
                    customer.setFullName(rs.getString("fullName"));
                    customer.setAddress(rs.getString("adress"));
                    customer.setPhoneNumber(rs.getString("phoneNumber"));
                    customer.setProfessional(rs.getBoolean("isProfessional"));
                    return Optional.of(customer);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


    @Override
    public void saveCustomer(Customer customer){
        String query = "INSERT INTO customers VALUES(?,?,?,?)";
        try(PreparedStatement stmt = cnx.prepareStatement(query)){
            stmt.setString(1, customer.getFullName());
            stmt.setString(2, customer.getAddress());
            stmt.setString(3, customer.getPhoneNumber());
            stmt.setBoolean(4, customer.isProfessional());
            stmt.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }



}
