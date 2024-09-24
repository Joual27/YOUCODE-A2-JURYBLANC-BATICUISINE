package ma.youcoude.batiCuisine.customer;

import ma.youcoude.batiCuisine.customer.interfaces.CustomerRepositoryI;
import ma.youcoude.batiCuisine.database.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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


    @Override
    public List<Customer> findAllCustomers(){
        String query = "SELECT * FROM customers";
        List<Customer> customers = new ArrayList<>();
        try(PreparedStatement stmt = cnx.prepareStatement(query)){
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setFullName(rs.getString("fullName"));
                customer.setAddress(rs.getString("adress"));
                customer.setPhoneNumber(rs.getString("phoneNumber"));
                customer.setProfessional(rs.getBoolean("isProfessional"));
                customers.add(customer);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public void UpdateCustomer(Customer customer){
        String query = "UPDATE customer SET adress = ?, phoneNumber = ? , isProfessional = ? WHERE fullname = ?";
        try(PreparedStatement stmt = cnx.prepareStatement(query)){
            stmt.setString(1, customer.getAddress());
            stmt.setString(2, customer.getPhoneNumber());
            stmt.setBoolean(3, customer.isProfessional());
            stmt.setString(4, customer.getFullName());
            stmt.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCustomer(Customer customer){

    }




}
