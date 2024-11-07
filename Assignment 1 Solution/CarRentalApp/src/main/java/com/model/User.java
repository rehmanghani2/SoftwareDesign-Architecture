/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.model;

/**
 *
 * @author Ghani
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    private String userID;
    private String name;
    private String email;
    private String contactNumber;
    private PaymentDetails paymentInfo;

    
    public User(String userID,String name,String email, String contactNumber){
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.contactNumber = contactNumber;
    }
    public User(String userrID) {
        this.userID = userrID;
        // Load user details from the database
        loadUserDetails(userID);
    }

    // Method to load user details from the database
    private void loadUserDetails(String userID) {
        String dbUrl = "jdbc:mysql://127.0.0.1:3306/carrentalapp"; // Replace with your database URL
        String dbUser = "root"; // Replace with your database username
        String dbPassword = "@hm@dz@i123"; // Replace with your database password

        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            String query = "SELECT name, email, contact_number FROM users WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, userID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                this.name = resultSet.getString("name");
                this.email = resultSet.getString("email");
                this.contactNumber = resultSet.getString("contact_number");
                // Assuming a separate method or table exists for payment details
                loadPaymentInfo(userID);
            } else {
                System.out.println("User not found with ID: " + userID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to load payment information for the user
    private void loadPaymentInfo(String userID) {
        String dbUrl = "jdbc:mysql://127.0.0.1:3306/carrentalapp"; // Replace with your database URL
        String dbUser = "root"; // Replace with your database username
        String dbPassword = "@hm@dz@i123"; // Replace with your database password

        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            String query = "SELECT payment_id,payment_method, card_number, expiry_date,amount FROM payment_details WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, userID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String payment_id = resultSet.getString("payment_id");
                String paymentMethod = resultSet.getString("payment_method");
                String cardNumber = resultSet.getString("card_number");
                String expiryDate = resultSet.getString("expiry_date");
                String amount = resultSet.getString("amount");
                this.paymentInfo = new PaymentDetails(payment_id,paymentMethod, cardNumber, expiryDate,amount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get user details
    public User getUserDetails() {
        return new User(userID, name, email, contactNumber);
    }

    // Method to update contact information in the database
    public void updateContactInfo(String email, String contactNumber) {
        this.email = email;
        this.contactNumber = contactNumber;

        String dbUrl = "jdbc:mysql://127.0.0.1:3306/carrentalapp"; // Replace with your database URL
        String dbUser = "root"; // Replace with your database username
        String dbPassword = "@hm@dz@i123"; // Replace with your database password

        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            String query = "UPDATE users SET email = ?, contact_number = ? WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, contactNumber);
            statement.setString(3, userID);
            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("User contact information updated successfully.");
            } else {
                System.out.println("Failed to update user contact information.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to add payment information to the database
    public void addPaymentInfo(PaymentDetails paymentInfo) {
        this.paymentInfo = paymentInfo;

        String dbUrl = "jdbc:mysql://127.0.0.1:3306/carrentalapp"; // Replace with your database URL
        String dbUser = "root"; // Replace with your database username
        String dbPassword = "@hm@dz@i123"; // Replace with your database password

        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            String query = "INSERT INTO payment_details (user_id, payment_method, card_number, expiry_date) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, userID);
            statement.setString(2, paymentInfo.getPaymentMethod());
            statement.setString(3, paymentInfo.getCardNumber());
            statement.setString(4, paymentInfo.getExpiryDate());
            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Payment information added successfully.");
            } else {
                System.out.println("Failed to add payment information.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get payment information
    public PaymentDetails getPaymentInfo() {
        return this.paymentInfo;
    }
    
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
    public static void main(String[] args) {
        User user1 =new User("u001");
        user1.loadPaymentInfo("u001");
        user1.loadUserDetails("u001");
        user1.updateContactInfo("ghani@gmail", "03011111111");
    }
}