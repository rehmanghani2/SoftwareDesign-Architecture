/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.view;

//import java.awt.BorderLayout;
//import java.sql.DriverManager;
//import javax.swing.JFrame;
//import javax.swing.JScrollPane;
//import javax.swing.JTextArea;
//import main.java.com.model.Car;
//import main.resources.carDatabase;
//
///**
// *
// * @author Ghani
// */
//public class CarDetailsGUI {
//    public static void showCarDetailsFrame() {
//        JFrame carDetailsFrame = new JFrame("Car Details");
//        carDetailsFrame.setSize(400, 300);
//        carDetailsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        carDetailsFrame.setLayout(new BorderLayout());
//
//        JTextArea carDetailsArea = new JTextArea();
//        carDetailsArea.setEditable(false);
//        String DB_URL = "jdbc:mysql://127.0.0.1:3306/?user=root";
//        // Load and display car details
//        carDatabase carDb = null;
//        try{
//        carDb = new carDatabase(DriverManager.getConnection(DB_URL));
//        }catch(Exception e){
//            System.out.println(e.toString());
//        }
//        StringBuilder carDetails = new StringBuilder();
//        for (Car car : carDb.getAllCars()) {
//            carDetails.append(car.getDetails()).append("\n");
//        }
//        carDetailsArea.setText(carDetails.toString());
//        
//        carDetailsFrame.add(new JScrollPane(carDetailsArea), BorderLayout.CENTER);
//        carDetailsFrame.setVisible(true);
//    }
//}


import javax.swing.*;
import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import main.java.com.model.Car;
import main.java.com.model.CarDetails;

public class CarDetailsGUI extends JFrame {
    private JLabel carImageLabel;
    private JLabel carDetailsLabel;

    public CarDetailsGUI(Car car) {
        setTitle("Car Details");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Display Car Image
        carImageLabel = new JLabel();
        String imagePath = car.getImagePath();  // Example: "src/resources/images/car1.jpg"
        displayCarImage(imagePath);

        // Display Car Details
        Car carDetails= car.getDetails();
        String details = "<html>Car ID: " + carDetails.getCarID()  + "<br>" +  
                    "Car Model: " + carDetails.getModel() + "<br>" +
                     "Rental Price per Day: $" + carDetails.getRentalPricePerDay() + "<br>" +
                         "Location: " + carDetails.getLocation() + "<br>" +
                        // "Availability: " + (car.checkAvailability("2/2/2003", "12/122024") ? "Available" : "Unavailable") +
                        //"Availability: " + (car.checkAvailability(new Date(), new Date()) ? "Available" : "Unavailable") +
                         "Availability: " + car.getAvailabilityStatus() + "<br>" +
                         "RentalPricePerDay : " + car.getRentalPricePerDay() +
                        "</html>";
        carDetailsLabel = new JLabel(details);
        carDetailsLabel.setVerticalAlignment(SwingConstants.TOP);
        carDetailsLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        carDetailsLabel.setFont(new Font("Arial",Font.BOLD,24));

        // Add components to the frame
        add(carImageLabel, BorderLayout.CENTER);
        add(carDetailsLabel, BorderLayout.SOUTH);
    }
    
    private void displayCarImage(String imagePath) {
        try {
            BufferedImage img = ImageIO.read(new File(imagePath));
            ImageIcon icon = new ImageIcon(img.getScaledInstance(400, 300, Image.SCALE_SMOOTH));
            carImageLabel.setIcon(icon);
        } catch (IOException e) {
            carImageLabel.setText("Image not found");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Example usage
        Car car = new Car("c001");  // Assuming this car ID is present in the database
        System.out.println(car.getImagePath());
        CarDetailsGUI frame = new CarDetailsGUI(car);
        frame.setVisible(true);
    }
}