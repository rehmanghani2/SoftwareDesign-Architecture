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
import java.util.Date;

public class Car {
    private String carID;
    private String model;
    private double rentalPricePerDay;
    private String availabilityStatus;
    private String location;
    private String imagePath; // Optional: Path to image if needed in GUI

    // Constructor to load car details from the database
    public Car(String carID) {
        this.carID = carID;
        loadCarDetails();
    }
     public Car(String carID,String model,double rentalPricePerDay,String availabilityStatus,String location, String imagePath){
         this.carID = carID;
         this.location=location;
         this.availabilityStatus= availabilityStatus;
         this.model = model;
         this.imagePath = imagePath;
     }
      public Car(String carID, String model, double rentalPricePerDay, String location, String imagePath) {
        this.carID = carID;
        this.model = model;
        this.rentalPricePerDay = rentalPricePerDay;
        this.location = location;
        this.availabilityStatus = availabilityStatus;    
        this.imagePath = imagePath;
    }
    


    // Load car details from the database based on carID
    private void loadCarDetails() {
        String dbUrl = "jdbc:mysql://127.0.0.1:3306/carrentalapp"; // Replace with your database URL
        String dbUser = "root"; // Replace with your database username
        String dbPassword = "@hm@dz@i123"; // Replace with your database password
        
        try (Connection connection = DriverManager.getConnection(dbUrl,dbUser,dbPassword)){
             String query = "SELECT model, rentalPricePerDay, availabilityStatus, location, imagePath FROM cars WHERE carID = ?";
             PreparedStatement statement = connection.prepareStatement(query); 
            statement.setString(1, carID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                this.model = resultSet.getString("model");
                this.rentalPricePerDay = resultSet.getDouble("rentalPricePerDay");
                this.availabilityStatus = resultSet.getString("availabilityStatus");
                this.location = resultSet.getString("location");
                this.imagePath = resultSet.getString("imagePath");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Check if the car is available for the given date range
    public boolean checkAvailability(Date startDate, Date endDate) {
        return "Available".equals(this.availabilityStatus);
    }

    // Update car status in the database
    public void updateStatus(String status, Date startDate, Date endDate) {
        String dbUrl = "jdbc:mysql://127.0.0.1:3306/carrentalapp"; // Replace with your database URL
        String dbUser = "root"; // Replace with your database username
        String dbPassword = "@hm@dz@i123"; // Replace with your database password
        this.availabilityStatus = status;      

        try (Connection connection = DriverManager.getConnection(dbUrl,dbUser,dbPassword)){
           String updateQuery = "UPDATE cars SET availabilityStatus = ? WHERE carID = ?";
          PreparedStatement statement = connection.prepareStatement(updateQuery);

            statement.setString(1, status);
            statement.setString(2, carID);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Return car details
    public Car getDetails() {
        return new Car(carID, model, rentalPricePerDay, location,imagePath);
    }

    public String getImagePath() {
        return imagePath;
    }

    // Getters and setters for other fields if necessary
    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getRentalPricePerDay() {
        return rentalPricePerDay;
    }

    public void setRentalPricePerDay(double rentalPricePerDay) {
        this.rentalPricePerDay = rentalPricePerDay;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    @Override
    public String toString() {
        return "CarDetails{" +
                "carID='" + carID + '\'' +
                ", model='" + model + '\'' +
                ", rentalPricePerDay=" + rentalPricePerDay +
                ", location='" + location + '\'' +
                ", availabilityStatus='" + availabilityStatus + '\'' +
                ", imagePath='" + imagePath +'\''+
                '}';
    }
    public static void main(String[] args) {
        Car c = new Car("c001");
        System.out.println(c.toString());
    }
}














//import java.util.Date;
//
//public class Car {
//    private String carID;
//    private String model;
//    private double rentalPricePerDay;
//    private String availabilityStatus;
//    private String location;
//
//    public Car(String carID) {
//        this.carID = carID;
//        // Assuming a method to load car details from a data source
//        loadCarDetails(carID);
//    }
//     public Car(String carID,String model,double rentalPricePerDay,String availabilityStatus,String location){
//         this.carID = carID;
//         this.location=location;
//         this.availabilityStatus= availabilityStatus;
//         this.model = model;
//     }
//    // Loads car details from a database or data source (simulated here)
//    private void loadCarDetails(String carID) {
//        // Simulated loading of car data. In a real scenario, fetch details from a database.
//        // Example data for demonstration purposes
//        this.model = "Toyota Camry"; // Example model
//        this.rentalPricePerDay = 50.0; // Example price
//        this.availabilityStatus = "Available"; // Example availability status
//        this.location = "Downtown"; // Example location
//    }
//
//    // Method to check the availability of the car for a specific date range
//    public boolean checkAvailability(Date startDate, Date endDate) {
//        // The check can be more complex based on existing bookings or rental records.
//        // For simplicity, we assume the car's availability status is "Available" or "Unavailable".
//        return "Available".equalsIgnoreCase(availabilityStatus);
//    }
//
//    // Method to update the car's status (available, rented, under maintenance, etc.)
//    public void updateStatus(String status, Date startDate, Date endDate) {
//        this.availabilityStatus = status;
//        // In a real implementation, this should update the data in the database or a data source.
//        System.out.println("Car status updated to " + status + " for dates " + startDate + " to " + endDate);
//    }
//
//    // Method to return the car details
//    public CarDetails getDetails() {
//        return new CarDetails(carID, model, rentalPricePerDay, location);
//    }
//
//    // Getters and setters for the car's fields
//    public String getCarID() {
//        return carID;
//    }
//
//    public String getModel() {
//        return model;
//    }
//
//    public double getRentalPricePerDay() {
//        return rentalPricePerDay;
//    }
//
//    public String getAvailabilityStatus() {
//        return availabilityStatus;
//    }
//
//    public String getLocation() {
//        return location;
//    }
//
//    public void setModel(String model) {
//        this.model = model;
//    }
//
//    public void setRentalPricePerDay(double rentalPricePerDay) {
//        this.rentalPricePerDay = rentalPricePerDay;
//    }
//
//    public void setAvailabilityStatus(String availabilityStatus) {
//        this.availabilityStatus = availabilityStatus;
//    }
//
//    public void setLocation(String location) {
//        this.location = location;
//    }
//}
