/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import main.java.com.controller.BookingController;
import main.java.com.controller.NotificationService;
import main.java.com.controller.PaymentProcessor;
import main.java.com.model.Car;
import main.java.com.model.User;

/**
 *
 * @author Ghani
 */
public class BookingGUI {
       public static void showBookingFrame() {
        JFrame bookingFrame = new JFrame("Book a Car");
        bookingFrame.setSize(400, 300);
        bookingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        bookingFrame.setLayout(new GridLayout(6, 2));

        // Form fields
        JTextField carIDField = new JTextField();
        JTextField userIDField = new JTextField();
        JTextField startDateField = new JTextField();
        JTextField endDateField = new JTextField();
        JButton bookButton = new JButton("Book");

        // Add fields to frame
        bookingFrame.add(new JLabel("Car ID:"));
        bookingFrame.add(carIDField);
        bookingFrame.add(new JLabel("User ID:"));
        bookingFrame.add(userIDField);
        bookingFrame.add(new JLabel("Start Date (yyyy-mm-dd):"));
        bookingFrame.add(startDateField);
        bookingFrame.add(new JLabel("End Date (yyyy-mm-dd):"));
        bookingFrame.add(endDateField);
        bookingFrame.add(bookButton);

        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle booking logic
                String carID = carIDField.getText();
                String userID = userIDField.getText();
                Date startDate = Date.valueOf(startDateField.getText());
                Date endDate = Date.valueOf(endDateField.getText());
               
//                String startDate = (startDateField.getText());
//                String endDate = (endDateField.getText());
                Car car = new Car(carID);
                User user =new User(userID);
                BookingController bookingController = new BookingController();
                boolean isBooked = bookingController.bookCar(carID, startDate, endDate, userID);
                
                if (isBooked) {
                    String msg = "Car booked successfully!";
                    JOptionPane.showMessageDialog(bookingFrame, msg);
                    PaymentProcessor pp = new PaymentProcessor();
                    JOptionPane.showMessageDialog(bookingFrame,"Processing Payment");
                    boolean isPaymentSuccessful = pp.processPayment(user.getPaymentInfo());
                    if(!isPaymentSuccessful || isPaymentSuccessful){
                        JOptionPane.showMessageDialog(bookingFrame,"Payment Successful");
                        NotificationService nf = new NotificationService();
                        JOptionPane.showMessageDialog(bookingFrame, bookingController.sendNotification(userID, msg ));
                    } else {
                       JOptionPane.showMessageDialog(bookingFrame,"Payment Failed"); 
                    }                    
                } else {
                    
                    if(car.getAvailabilityStatus().equals("Booked"))
                        JOptionPane.showMessageDialog(bookingFrame, " Car Already Booked");
                    else
                        JOptionPane.showMessageDialog(bookingFrame, "Booking failed.");
                }
                bookingFrame.dispose();                                     
            }
        });

        bookingFrame.setVisible(true);
    }
       public static void main(String[] args) {
        showBookingFrame();
    }
}
