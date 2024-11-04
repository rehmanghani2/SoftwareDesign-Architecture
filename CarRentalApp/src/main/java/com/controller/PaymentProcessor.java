/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import main.java.com.model.PaymentDetails;

/**
 *
 * @author Ghani
 */
public class PaymentProcessor {
    private String paymentGateway;
    private String transactionID;

    public PaymentProcessor(String paymentGateway) {
        this.paymentGateway = paymentGateway;
    }
    public PaymentProcessor() {
        this.paymentGateway = paymentGateway;
    }
    // Method to process the payment with the payment gateway
    public boolean processPayment(PaymentDetails paymentInfo) {
        // Simulate payment processing
        System.out.println("Processing payment through " + paymentGateway + " for card number: " + paymentInfo.getCardNumber());

        // Simulated response from the payment gateway
        boolean paymentSuccessful = Math.random() > 0.2; // 80% chance of success
        if (paymentSuccessful) {
            this.transactionID = generateTransactionID();
            System.out.println("Payment successful. Transaction ID: " + transactionID);
            // Record payment transaction details in the database
            recordTransaction(transactionID, paymentInfo, "SUCCESS");
        } else {
            System.out.println("Payment failed.");
            recordTransaction(null, paymentInfo, "FAILED");
        }
        return paymentSuccessful;
    }
     public boolean processPayment(String userID) {
         PaymentDetails paymentInfo = new PaymentDetails();
        // Simulate payment processing
        System.out.println("Processing payment through " + paymentGateway + " for card number: " + paymentInfo.getCardNumber());

        // Simulated response from the payment gateway
        boolean paymentSuccessful = Math.random() > 0.2; // 80% chance of success
        if (paymentSuccessful) {
            this.transactionID = generateTransactionID();
            System.out.println("Payment successful. Transaction ID: " + transactionID);
            // Record payment transaction details in the database
          //  recordTransaction(transactionID, paymentInfo, "SUCCESS");
        } else {
            System.out.println("Payment failed.");
            recordTransaction(null, paymentInfo, "FAILED");
        }
        return paymentSuccessful;
    }
    

    // Method to refund a payment
    public boolean refundPayment(String transactionID) {
        // Simulate a refund process
        System.out.println("Refunding payment for Transaction ID: " + transactionID);

        // Simulated response from payment gateway
        boolean refundSuccessful = Math.random() > 0.1; // 90% chance of success
        if (refundSuccessful) {
            System.out.println("Refund successful for Transaction ID: " + transactionID);
            // Update transaction status in the database
            updateTransactionStatus(transactionID, "REFUNDED");
        } else {
            System.out.println("Refund failed for Transaction ID: " + transactionID);
        }
        return refundSuccessful;
    }

    // Method to verify payment status
    public boolean verifyPaymentStatus(String transactionID) {
        // Simulate checking the payment status
        System.out.println("Verifying payment status for Transaction ID: " + transactionID);

        // Simulated response from payment gateway
        boolean isSuccessful = Math.random() > 0.2; // 80% chance the payment is confirmed
        String status = isSuccessful ? "CONFIRMED" : "NOT CONFIRMED";

        System.out.println("Payment status: " + status);
        return isSuccessful;
    }

    // Helper method to generate a mock transaction ID
    private String generateTransactionID() {
        return "TXN" + System.currentTimeMillis();
    }

    // Helper method to record a transaction in the database
    private void recordTransaction(String transactionID, PaymentDetails paymentInfo, String status) {
        String dbUrl = "jdbc:mysql://127.0.0.1:3306/carrentalapp"; // Replace with your database URL
        String dbUser = "root"; // Replace with your database username
        String dbPassword = "@hm@dz@i123"; // Replace with your database password

        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            String query = "INSERT INTO transactions (transaction_id, user_id, card_number, amount, status) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, transactionID);
            statement.setString(2, paymentInfo.getCardHolderName());
            statement.setString(3, paymentInfo.getCardNumber());
           statement.setString(4, paymentInfo.getAmount());
          // statement.setString(4, paymentInfo.getPaymentInfoID());
            statement.setString(5, status);
            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Transaction recorded successfully.");
            } else {
                System.out.println("Failed to record transaction.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Helper method to update the status of a transaction
    private void updateTransactionStatus(String transactionID, String status) {
        String dbUrl = "jdbc:mysql://127.0.0.1:3306/carrentalapp"; // Replace with your database URL
        String dbUser = "root"; // Replace with your database username
        String dbPassword = "@hm@dz@i123"; // Replace with your database password

        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            String query = "UPDATE transactions SET status = ? WHERE transaction_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, status);
            statement.setString(2, transactionID);
            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Transaction status updated successfully.");
            } else {
                System.out.println("Failed to update transaction status.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
