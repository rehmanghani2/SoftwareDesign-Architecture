/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.model;

import java.util.Date;
import main.resources.carDatabase;

/**
 *
 * @author Ghani
 */

import java.sql.*;
import java.util.Date;

public class RentalAgreement {
    private String agreementID;
    private String carID;
    private String userID;
    private Date startDate;
    private Date endDate;
    private double totalCost;

    // Database connection details
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/carrentalapp";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "@hm@dz@i123";

    // Constructor to generate a new rental agreement
    public RentalAgreement(String carID, String userID, Date startDate, Date endDate) {
        this.carID = carID;
        this.userID = userID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalCost = calculateTotalCost();
        this.agreementID = generateAgreementID();
        saveAgreementToDatabase(); // Save to the database upon creation
    }

    // Constructor to retrieve an existing rental agreement
    public RentalAgreement(String agreementID, String carID, String userID, Date startDate, Date endDate, double totalCost) {
        this.agreementID = agreementID;
        this.carID = carID;
        this.userID = userID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalCost = totalCost;
    }
    
     public RentalAgreement() {
        this.carID = carID;
        this.userID = userID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalCost = calculateTotalCost();
        this.agreementID = generateAgreementID();
    }
     
   //   Method to generate a new rental agreement
    public void generateAgreement(String carID, String userID, Date startDate, Date endDate) {
        this.agreementID = generateAgreementID(); // Generate a unique agreement ID
        this.carID = carID;
        this.userID = userID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalCost = calculateTotalCost(); // Calculate the total cost based on car rental price and rental days

        // Optionally, save the agreement details to a database or file here
        System.out.println("Rental agreement generated with ID: " + agreementID);
    }

    // Calculate the total rental cost based on car's daily price and rental duration
    private double calculateTotalCost() {
        Car car = carDatabase.getCarById(carID); // Assuming CarDatabase provides car details
        if (car == null) {
            throw new IllegalArgumentException("Invalid car ID");
        }
        
        long durationInMillis = endDate.getTime() - startDate.getTime();
        long rentalDays = (durationInMillis / (1000 * 60 * 60 * 24)) + 1; // Including both start and end dates

        return rentalDays * car.getRentalPricePerDay();
    }

    // Generate a unique agreement ID
    private String generateAgreementID() {
        return "AGMT" + System.currentTimeMillis();
    }

    // Save the rental agreement to the database
    private void saveAgreementToDatabase() {
        String sql = "INSERT INTO rental_agreements (agreementID, carID, userID, startDate, endDate, totalCost) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, agreementID);
            pstmt.setString(2, carID);
            pstmt.setString(3, userID);
            pstmt.setDate(4, new java.sql.Date(startDate.getTime()));
            pstmt.setDate(5, new java.sql.Date(endDate.getTime()));
            pstmt.setDouble(6, totalCost);

            pstmt.executeUpdate();
            System.out.println("Rental agreement saved to the database with ID: " + agreementID);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to save rental agreement to the database.");
        }
    }

    // Retrieve an agreement by ID
    public static RentalAgreement getAgreementByID(String agreementID) {
        String sql = "SELECT * FROM rental_agreements WHERE agreementID = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, agreementID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String carID = rs.getString("carID");
                String userID = rs.getString("userID");
                Date startDate = rs.getDate("startDate");
                Date endDate = rs.getDate("endDate");
                double totalCost = rs.getDouble("totalCost");

                return new RentalAgreement(agreementID, carID, userID, startDate, endDate, totalCost);
            } else {
                System.out.println("Rental agreement not found with ID: " + agreementID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Cancel the rental agreement
    public void cancelAgreement() {
        String sql = "DELETE FROM rental_agreements WHERE agreementID = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, agreementID);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Rental agreement " + agreementID + " has been canceled.");
            } else {
                System.out.println("No agreement found with ID: " + agreementID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to cancel rental agreement.");
        }
    }

    // Getters and Setters
    public String getAgreementID() {
        return agreementID;
    }

    public String getCarID() {
        return carID;
    }

    public String getUserID() {
        return userID;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public double getTotalCost() {
        return totalCost;
    }

    // ToString for Rental Agreement details
    @Override
    public String toString() {
        return "AgreementDetails{" +
                "agreementID='" + agreementID + '\'' +
                ", carID='" + carID + '\'' +
                ", userID='" + userID + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", totalCost=" + totalCost +
                '}';
    }
}




//public class RentalAgreement {
//    private String agreementID;
//    private String carID;
//    private String userID;
//    private Date startDate;
//    private Date endDate;
//    private double totalCost;
//
//    public RentalAgreement(String carID, String userID, Date startDate, Date endDate) {
//        this.carID = carID;
//        this.userID = userID;
//        this.startDate = startDate;
//        this.endDate = endDate;
//        this.totalCost = calculateTotalCost();
//        this.agreementID = generateAgreementID();
//    }
//        public RentalAgreement() {
//        this.carID = carID;
//        this.userID = userID;
//        this.startDate = startDate;
//        this.endDate = endDate;
//        this.totalCost = calculateTotalCost();
//        this.agreementID = generateAgreementID();
//    }
//
//    // Calculate the total rental cost based on the car's daily price and rental duration
//    private double calculateTotalCost() {
//        Car car = new Car(carID);
//        Car carDetails = car.getDetails();
//        long durationInMillis = endDate.getTime() - startDate.getTime();
//        long rentalDays = (durationInMillis / (1000 * 60 * 60 * 24)) + 1; // Including both start and end days
//        return rentalDays * car.getRentalPricePerDay();
//    }
//
//    // Generate a mock agreement ID
//    private String generateAgreementID() {
//        return "AGMT" + System.currentTimeMillis();
//    }
//
//    // Get the details of the rental agreement
//    public AgreementDetails getAgreementDetails() {
//        return new AgreementDetails(agreementID, carID, userID, startDate, endDate, totalCost);
//    }
//
//    // Cancel the rental agreement
//    public void cancelAgreement() {
//        // Update the agreement status in the database (simulated here)
//        System.out.println("Rental agreement " + agreementID + " for car " + carID + " has been canceled.");
//        // Additional logic to update status or inform the user can be added
//    }
//    // Method to generate a new rental agreement
//    public void generateAgreement(String carID, String userID, Date startDate, Date endDate) {
//        this.agreementID = generateAgreementID(); // Generate a unique agreement ID
//        this.carID = carID;
//        this.userID = userID;
//        this.startDate = startDate;
//        this.endDate = endDate;
//        this.totalCost = calculateTotalCost(); // Calculate the total cost based on car rental price and rental days
//
//        // Optionally, save the agreement details to a database or file here
//        System.out.println("Rental agreement generated with ID: " + agreementID);
//    }
//
//    // Helper method to calculate the total rental cost based on the rental duration and car's daily price
//    public double getTotalCost() {
//        Car car = carDatabase.getCarById(carID); // Get car details from the CarDatabase
//        if (car == null) {
//            throw new IllegalArgumentException("Invalid car ID");
//        }
//        
//        long durationInMillis = endDate.getTime() - startDate.getTime();
//        long rentalDays = (durationInMillis / (1000 * 60 * 60 * 24)) + 1; // Include both start and end dates
//
//        return rentalDays * car.getRentalPricePerDay();
//    }
//     public RentalAgreement(String agreementID, String carID, String userID, Date startDate, Date endDate, double totalCost) {
//        this.agreementID = agreementID;
//        this.carID = carID;
//        this.userID = userID;
//        this.startDate = startDate;
//        this.endDate = endDate;
//        this.totalCost = totalCost;
//    }
//
//    // Getters and Setters
//    public String getAgreementID() {
//        return agreementID;
//    }
//
//    public void setAgreementID(String agreementID) {
//        this.agreementID = agreementID;
//    }
//
//    public String getCarID() {
//        return carID;
//    }
//
//    public void setCarID(String carID) {
//        this.carID = carID;
//    }
//
//    public String getUserID() {
//        return userID;
//    }
//
//    public void setUserID(String userID) {
//        this.userID = userID;
//    }
//
//    public Date getStartDate() {
//        return startDate;
//    }
//
//    public void setStartDate(Date startDate) {
//        this.startDate = startDate;
//    }
//
//    public Date getEndDate() {
//        return endDate;
//    }
//
//    public void setEndDate(Date endDate) {
//        this.endDate = endDate;
//    }
//
////    public double getTotalCost() {
////        return totalCost;
////    }
//
//    public void setTotalCost(double totalCost) {
//        this.totalCost = totalCost;
//    }
//
//    @Override
//    public String toString() {
//        return "AgreementDetails{" +
//                "agreementID='" + agreementID + '\'' +
//                ", carID='" + carID + '\'' +
//                ", userID='" + userID + '\'' +
//                ", startDate=" + startDate +
//                ", endDate=" + endDate +
//                ", totalCost=" + totalCost +
//                '}';
//    }
//}
