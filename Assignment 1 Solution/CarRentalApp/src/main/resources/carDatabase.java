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
import java.util.ArrayList;
import main.java.com.model.Car;


public class carDatabase {   
  //  private static final String DB_URL = "jdbc:mysql://localhost:3306/car_rental";
     private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/carrentalapp";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "@hm@dz@i123";
     private Connection connection;

     public carDatabase() {
        this.connection = connection;
    }
    public carDatabase(Connection connection) {
        this.connection = connection;
    }
    // Method to retrieve a Car by its ID from the database
    public static Car getCarById(String carID) {
        Car car = null;
        try {
    Class.forName("com.mysql.cj.jdbc.Driver");
} catch (ClassNotFoundException e) {
    e.printStackTrace();
}

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT * FROM cars WHERE carID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, carID);
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                String model = resultSet.getString("model");
                double rentalPricePerDay = resultSet.getDouble("rentalPricePerDay");
                String availabilityStatus = resultSet.getString("availabilityStatus");
                String location = resultSet.getString("location");
                String imagePath = resultSet.getString("imagePath");
                car = new Car(carID, model, rentalPricePerDay, availabilityStatus, location,imagePath);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }       
        return car;
    }
    public  ArrayList<Car> getAllCars() {   
      //  private Connection connection;
       
        ArrayList<Car> cars = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)){
//        } catch(SQLException e){
//            e.printStackTrace();
//        }
            
            String sql = "SELECT * FROM cars";
//            PreparedStatement preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setString(1, carID);
       // String sql = "SELECT * FROM cars";
        

        PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String carID = resultSet.getString("carID");
                String model = resultSet.getString("model");
                double rentalPricePerDay = resultSet.getDouble("rentalPricePerDay");
                String availabilityStatus = resultSet.getString("availabilityStatus");
                String location = resultSet.getString("location");

                Car car = new Car(carID);
                car.setModel(model);
                car.setRentalPricePerDay(rentalPricePerDay);
                car.setAvailabilityStatus(availabilityStatus);
                car.setLocation(location);

                cars.add(car);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving cars: " + e.getMessage());
        }
        return cars;
    }
    public static void main(String[] args) throws Exception {
        String URL_DB  = "jdbc:mysql://127.0.0.1:3306/carrentalapp?user=root&password=@hm@dz@i123";
        carDatabase cd = new carDatabase();
        System.out.println(cd.getCarById("c001").getModel());
        System.out.println(cd.getCarById("c001").getCarID());
        System.out.println(cd.getCarById("c001").getLocation());
        System.out.println(cd.getCarById("c001").getRentalPricePerDay());
        System.out.println(cd.getAllCars());
    }
}
