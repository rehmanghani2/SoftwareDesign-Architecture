/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.view;

/**
 *
 * @author Ghani
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import main.java.com.controller.BookingController;
import main.java.com.controller.PaymentProcessor;
import main.java.com.model.Car;
import main.java.com.model.RentalAgreement;
import main.resources.carDatabase;

public class UserInterface extends JFrame {
    private String currentUserID;
    private String selectedCarID;
    private Date rentalStartDate;
    private Date rentalEndDate;
    
    // GUI components
    private JTextField carIDField, startDateField, endDateField;
    private JButton displayCarDetailsButton, bookCarButton, showPaymentOptionsButton;

    public UserInterface(String currentUserID) {
        this.currentUserID = currentUserID;
        setTitle("Car Rental System");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initializeUI();
    }

    private void initializeUI() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        // Car ID input and button to display car details
        panel.add(new JLabel("Car ID:"));
        carIDField = new JTextField();
        panel.add(carIDField);

        displayCarDetailsButton = new JButton("Display Car Details");
        displayCarDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayCarDetails(carIDField.getText());
            }
        });
        panel.add(displayCarDetailsButton);

        // Date fields for rental
        panel.add(new JLabel("Start Date (yyyy-mm-dd):"));
        startDateField = new JTextField();
        panel.add(startDateField);

        panel.add(new JLabel("End Date (yyyy-mm-dd):"));
        endDateField = new JTextField();
        panel.add(endDateField);

        // Button to book car
        bookCarButton = new JButton("Book Car");
        bookCarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bookCar();
            }
        });
        panel.add(bookCarButton);

        // Button to show payment options
        showPaymentOptionsButton = new JButton("Show Payment Options");
        showPaymentOptionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showPaymentOptions();
            }
        });
        panel.add(showPaymentOptionsButton);

        // Add panel to frame
        add(panel);
    }

    // Display car details based on the car ID
    public void displayCarDetails(String carID) {
        Car car = carDatabase.getCarById(carID);
        if (car != null) {
            JOptionPane.showMessageDialog(this,
                "Car ID: " + car.getCarID() + "\n" +
                "Model: " + car.getModel() + "\n" +
                "Rental Price Per Day: $" + car.getRentalPricePerDay() + "\n" +
                "Location: " + car.getLocation() + "\n" +
                "Availability Status: " + car.getAvailabilityStatus(),
                "Car Details",
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Car not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Book a car using the BookingController
    public void bookCar() {
        selectedCarID = carIDField.getText();
        rentalStartDate = parseDate(startDateField.getText());
        rentalEndDate = parseDate(endDateField.getText());

        if (selectedCarID.isEmpty() || rentalStartDate == null || rentalEndDate == null) {
            JOptionPane.showMessageDialog(this, "Please provide complete booking details.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        BookingController controller = new BookingController();

        // Check if the car is available for the selected dates
        if (!controller.checkAvailability(selectedCarID, rentalStartDate, rentalEndDate)) {
            JOptionPane.showMessageDialog(this, "The car is not available for the selected dates.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Process the booking
        controller.bookCar(selectedCarID, rentalStartDate, rentalEndDate, currentUserID);

        // Assuming RentalAgreement is generated after booking
        RentalAgreement agreement = new RentalAgreement();
        agreement.generateAgreement(selectedCarID, currentUserID, rentalStartDate, rentalEndDate);
        double totalCost = agreement.getTotalCost();

        JOptionPane.showMessageDialog(this,
                "Booking confirmed!\nRental Agreement Details:\n" +
                "Car ID: " + selectedCarID + "\n" +
                "User ID: " + currentUserID + "\n" +
                "Start Date: " + rentalStartDate + "\n" +
                "End Date: " + rentalEndDate + "\n" +
                "Total Cost: $" + totalCost,
                "Booking Confirmation",
                JOptionPane.INFORMATION_MESSAGE);
    }

    // Show payment options
    public void showPaymentOptions() {
        String[] options = {"Credit Card", "PayPal", "Bank Transfer"};
        int paymentChoice = JOptionPane.showOptionDialog(this, "Select a payment method:", "Payment Options",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        if (paymentChoice != JOptionPane.CLOSED_OPTION) {
            processPayment(paymentChoice + 1);
        }
    }

    // Process payment based on user's choice
    private void processPayment(int paymentChoice) {
        PaymentProcessor paymentProcessor = new PaymentProcessor();
        boolean paymentSuccess = false;

        switch (paymentChoice) {
            case 1:
                paymentSuccess = paymentProcessor.processPayment(currentUserID + "_creditcard");
                break;
            case 2:
                paymentSuccess = paymentProcessor.processPayment(currentUserID + "_paypal");
                break;
            case 3:
                paymentSuccess = paymentProcessor.processPayment(currentUserID + "_banktransfer");
                break;
            default:
                JOptionPane.showMessageDialog(this, "Invalid payment option.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
        }

        if (paymentSuccess) {
            JOptionPane.showMessageDialog(this, "Payment successful. Your booking is complete.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Payment failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Utility method to parse date from string
    private Date parseDate(String dateStr) {
        try {
            return new java.text.SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid date format. Please use yyyy-mm-dd.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new UserInterface("u001").setVisible(true);
        });
    }
}
















//import java.util.Date;
//import main.java.com.controller.BookingController;
//import main.java.com.controller.PaymentProcessor;
//import main.java.com.model.Car;
//import main.java.com.model.RentalAgreement;
//import main.resources.carDatabase;
//
//public class UserInterface {
//    private String currentUserID;
//    private String selectedCarID;
////    private String rentalStartDate;
////    private String rentalEndDate;
//    private Date rentalStartDate;
//    private Date rentalEndDate;
//
//    public UserInterface(String currentUserID) {
//        this.currentUserID = currentUserID;
//    }
//
//    // Method to display car details based on the car ID
//    public void displayCarDetails(String carID) {
//        Car car = carDatabase.getCarById(carID); // Assuming CarDatabase is a class managing car data
//      // Car car = CarDetails.getCarID();
//       if (car != null) {
//            System.out.println("Car ID: " + car.getCarID());
//            System.out.println("Model: " + car.getModel());
//            System.out.println("Rental Price Per Day: $" + car.getRentalPricePerDay());
//            System.out.println("Location: " + car.getLocation());
//            System.out.println("Availability Status: " + car.getAvailabilityStatus());
//        } else {
//            System.out.println("Car not found.");
//        }
//    }
//
//    // Method to book a car using the BookingController
//    public void bookCar(String carID, Date startDate, Date endDate, String userID) {
//        BookingController controller = new BookingController();
//        
//        // Check if the car is available for the selected dates
//        if (!controller.checkAvailability(carID, startDate, endDate)) {
//            displayConfirmationMessage("The car is not available for the selected dates.");
//            return;
//        }
//
//        // Process the booking
//        controller.bookCar(carID, startDate, endDate, userID);
//        this.selectedCarID = carID;
//        this.rentalStartDate = startDate;
//        this.rentalEndDate = endDate;
//
//        // Display booking confirmation
//        confirmBookingDetails();
//    }
//
//    // Method to confirm booking details
//    public void confirmBookingDetails() {
//        if (selectedCarID == null || rentalStartDate == null || rentalEndDate == null || currentUserID == null) {
//            displayConfirmationMessage("Booking details are incomplete.");
//            return;
//        }
//
//        // Assuming RentalAgreement is generated after booking
//        RentalAgreement agreement = new RentalAgreement();
//        agreement.generateAgreement(selectedCarID, currentUserID, rentalStartDate, rentalEndDate);
//        double totalCost = agreement.getTotalCost();
//
//        displayConfirmationMessage("Booking confirmed! Rental Agreement Details:");
//        displayConfirmationMessage("Car ID: " + selectedCarID);
//        displayConfirmationMessage("User ID: " + currentUserID);
//        displayConfirmationMessage("Start Date: " + rentalStartDate);
//        displayConfirmationMessage("End Date: " + rentalEndDate);
//        displayConfirmationMessage("Total Cost: $" + totalCost);
//    }
//
//    // Method to show payment options to the user
//    public void showPaymentOptions() {
//        // Simulate showing payment options
//        System.out.println("Select a payment method:");
//        System.out.println("1. Credit Card");
//        System.out.println("2. PayPal");
//        System.out.println("3. Bank Transfer");
//        System.out.println("Please enter your choice:");
//
//        // Assume user inputs choice (simulated here for demonstration)
//        int paymentChoice = 1; // This should ideally be retrieved from user input
//        processPayment(paymentChoice);
//    }
//
//    // Helper method to process payment based on user's choice
//    private void processPayment(int paymentChoice) {
//        PaymentProcessor paymentProcessor = new PaymentProcessor();
//        boolean paymentSuccess = false;
//
//        switch (paymentChoice) {
//            case 1:
//            //    paymentSuccess = paymentProcessor.processPayment(currentUserID + "_creditcard");
//            //    paymentSuccess = paymentProcessor.processPayment(new pacreditcard);
//                break;
//            case 2:
//           //     paymentSuccess = paymentProcessor.processPayment(currentUserID + "_paypal");
//                break;
//            case 3:
//           //     paymentSuccess = paymentProcessor.processPayment(currentUserID + "_banktransfer");
//                break;
//            default:
//                displayConfirmationMessage("Invalid payment option.");
//                return;
//        }
//
//        if (paymentSuccess) {
//            displayConfirmationMessage("Payment successful. Your booking is complete.");
//        } else {
//            displayConfirmationMessage("Payment failed. Please try again.");
//        }
//    }
//
//    // Method to display confirmation messages
//    public void displayConfirmationMessage(String message) {
//        System.out.println(message);
//    }
//}
