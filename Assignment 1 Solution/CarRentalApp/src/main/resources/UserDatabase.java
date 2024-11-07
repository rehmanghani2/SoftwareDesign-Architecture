/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.resources;

/**
 *
 * @author Ghani
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import main.java.com.model.User;

public class UserDatabase {

    private Connection connection;
//
    public UserDatabase(Connection connection) {
        this.connection = connection;
    }

    // Method to get a user by their ID
    public User getUserById(String userID) {
       User user = null;
        try {
            String query = "SELECT * FROM users WHERE user_id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, userID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String contactNumber = rs.getString("contact_number");
              //  PaymentDetails paymentInfo = new PaymentDetails(rs.getString("paymentInfo"));

             //   user = new User(userID, name, email, contactNumber, paymentInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
     }
//
//    // Method to add a new user
    public boolean addUser(User user) {
        if(user.getEmail().equals("") || user.getName().equals("") || user.getUserID().equals("")
        || user.getContactNumber().equals("")){
            return false;
        }
        try {
            String query = "INSERT INTO users (user_id, name, email, contact_number) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, user.getUserID());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getContactNumber());
         //   stmt.setString(5, user.getPaymentInfo().getPaymentInfoID());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
//
//    // Method to update a user's contact information
    public boolean updateUserContactInfo(String userID, String email, String contactNumber) {
        try {
            String query = "UPDATE users SET email = ?, contact_Number = ? WHERE user_id = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, email);
            stmt.setString(2, contactNumber);
            stmt.setString(3, userID);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static void main(String[] args)throws Exception {
        UserDatabase udb = new UserDatabase(DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/carrentalapp?user=root&password=@hm@dz@i123"));
        udb.addUser(new User("2","ghani","ghani@gmail.com","03010010000"));
        udb.getUserById("1");
        udb.updateUserContactInfo("3", "ali@gmail.com","0301010101");
    }
}


