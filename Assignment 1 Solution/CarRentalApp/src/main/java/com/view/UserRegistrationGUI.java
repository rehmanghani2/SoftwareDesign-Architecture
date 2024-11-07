/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import main.java.com.model.User;
import main.resources.UserDatabase;

/**
 *
 * @author Ghani
 */
public class UserRegistrationGUI {
    public static void showUserRegistrationFrame() {
        JFrame registrationFrame = new JFrame("User Registration");
        registrationFrame.setSize(400, 300);
        registrationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        registrationFrame.setLayout(new GridLayout(5, 2));

        // Form fields
        JTextField userIDField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField contactField = new JTextField();
        JButton registerButton = new JButton("Register");

        // Add fields to frame
        registrationFrame.add(new JLabel("User ID:"));
        registrationFrame.add(userIDField);
        registrationFrame.add(new JLabel("Name:"));
        registrationFrame.add(nameField);
        registrationFrame.add(new JLabel("Email:"));
        registrationFrame.add(emailField);
        registrationFrame.add(new JLabel("Contact Number:"));
        registrationFrame.add(contactField);
        registrationFrame.add(registerButton);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle registration logic
                String DB_URL = "jdbc:mysql://127.0.0.1:3306/carrentalapp";
                 String DB_USER = "root";
                String DB_PASSWORD = "@hm@dz@i123";
                User newUser = new User(userIDField.getText());
                newUser.setName(nameField.getText());
                newUser.setEmail(emailField.getText());
                newUser.setContactNumber(contactField.getText());
                UserDatabase userDatabase = null;
                 
                  
                //     private Connection connection;
                try{
                 userDatabase = new UserDatabase(DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD));/* Pass your DB connection here */
                }catch(Exception error){
                    System.out.println(error.toString());
                }
                if (userDatabase.addUser(newUser)) {
                    JOptionPane.showMessageDialog(registrationFrame, "User registered successfully!");
                } else {
                    JOptionPane.showMessageDialog(registrationFrame, "Registration failed.");
                }
                registrationFrame.dispose();
            }
        });

        registrationFrame.setVisible(true);
    }
    public static void main(String[] args) {
        showUserRegistrationFrame() ;
    }
}
