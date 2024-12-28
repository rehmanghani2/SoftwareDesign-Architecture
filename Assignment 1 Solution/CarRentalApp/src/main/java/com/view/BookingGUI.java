/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.view;

import java.awt.Font;
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
import main.resources.UserDatabase;

/**
 *
 * @author Ghani
 */
public class BookingGUI {
       public static void showBookingFrame() {
        JFrame bookingFrame = new JFrame("Book a Car");
        bookingFrame.setSize(800, 500);
        bookingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        bookingFrame.setLayout(new GridLayout(6, 2));

        // Form fields
        JTextField carIDField = new JTextField();
        JTextField userIDField = new JTextField();
        JTextField startDateField = new JTextField();
        JTextField endDateField = new JTextField();
        JButton bookButton = new JButton("Book");
        JButton viewDetailsBtn = new JButton("View Car Details");
       // viewDetailsBtn.setFont(new Font("Arial",Font.BOLD,24));

        // Add fields to frame
        bookingFrame.add(new JLabel("Car ID:")).setFont(new Font("Arial",Font.BOLD,30));;
        bookingFrame.add(carIDField).setFont(new Font("Arial",Font.BOLD,30));
        bookingFrame.add(new JLabel("User ID:")).setFont(new Font("Arial",Font.BOLD,30));
        bookingFrame.add(userIDField).setFont(new Font("Arial",Font.BOLD,30));
        bookingFrame.add(new JLabel("Start Date (yyyy-mm-dd):")).setFont(new Font("Arial",Font.BOLD,30));
        bookingFrame.add(startDateField).setFont(new Font("Arial",Font.BOLD,30));
        bookingFrame.add(new JLabel("End Date (yyyy-mm-dd):")).setFont(new Font("Arial",Font.BOLD,30));
        bookingFrame.add(endDateField).setFont(new Font("Arial",Font.BOLD,30));
        bookingFrame.add(bookButton).setFont(new Font("Arial",Font.BOLD,30));
        bookingFrame.add(viewDetailsBtn).setFont(new Font("Arial",Font.BOLD,30));

        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle booking logic
                String carID = carIDField.getText();
                String userID = userIDField.getText();
                Date startDate = Date.valueOf(startDateField.getText()) ;
                Date endDate = Date.valueOf(endDateField.getText());
//               if(){
//                   JOptionPane.showMessageDialog(bookingFrame,"Enter date");
//               }
//                String startDate = (startDateField.getText());
//                String endDate = (endDateField.getText());
                Car car = new Car(carID);
                User user =new User(userID);
                BookingController bookingController = new BookingController();
                boolean isBooked = bookingController.bookCar(carID, startDate, endDate, userID);
                if(user.loadUserDetails(userID) ){
                   JOptionPane.showMessageDialog(bookingFrame,"<html><body><font size='5'>The user is registered</font></body></html>");
               }
                else {
                JOptionPane.showMessageDialog(bookingFrame," <html><body><font size='5'>The user is not  registered</font></body></html>");
            }
                if (isBooked) { // <html><body><font size='5'></font></body></html>
                    String msg = "<html><body><font size='5'>Car booked successfully!</font></body></html>";
                    JOptionPane.showMessageDialog(bookingFrame, msg);
                    PaymentProcessor pp = new PaymentProcessor();
                    JOptionPane.showMessageDialog(bookingFrame,"<html><body><font size='5'>Processing Payment</font></body></html>");
                    boolean isPaymentSuccessful = pp.processPayment(user.getPaymentInfo());
                    if(!isPaymentSuccessful || isPaymentSuccessful){
                        JOptionPane.showMessageDialog(bookingFrame,"<html><body><font size='5'>Payment Successful</font></body></html>");
                        NotificationService nf = new NotificationService();
                        JOptionPane.showMessageDialog(bookingFrame,"<html><body><font size='5'>"+bookingController.sendNotification(userID, msg )+"</font></body></html>");
                    } else {
                       JOptionPane.showMessageDialog(bookingFrame,"<html><body><font size='5'>Payment Failed</font></body></html> "); 
                    }                    
                } else {
                    
                    if(car.getAvailabilityStatus().equals("Booked"))
                        JOptionPane.showMessageDialog(bookingFrame, "<html><body><font size='5'>Car Already Booked</font></body></html> ");
                    else
                        JOptionPane.showMessageDialog(bookingFrame, "<html><body><font size='5'>Booking failed</font></body></html>");
                }
                bookingFrame.dispose();                                     
            }
        });
        
        viewDetailsBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String carID = carIDField.getText();
                CarDetailsGUI cd=new CarDetailsGUI(carID);
                cd.setVisible(true);
            }
        });
        
        
        
        bookingFrame.setVisible(true);
    }
       public static void main(String[] args) {
        showBookingFrame();
    }
}
