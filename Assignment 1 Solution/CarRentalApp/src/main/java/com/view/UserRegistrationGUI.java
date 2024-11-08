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
import java.sql.DriverManager;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import main.java.com.model.User;
import main.resources.UserDatabase;

/**
 *
 * @author Ghani
 */
public class UserRegistrationGUI {
    public static void showUserRegistrationFrame() {
        JFrame registrationFrame = new JFrame("User Registration");
        registrationFrame.setSize(600, 500);
        registrationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        registrationFrame.setLayout(new GridLayout(5, 2));
        
         UIManager.put("OptionPane.font",new java.awt.Font("Arial",java.awt.Font.BOLD,30));
        // Form fields
        JTextField userIDField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField contactField = new JTextField();
        JButton registerButton = new JButton("Register");
        

        // Add fields to frame
        registrationFrame.add(new JLabel("User ID:")).setFont(new Font("Arial",Font.BOLD,30));
        registrationFrame.add(userIDField).setFont(new Font("Arial",Font.BOLD,30));
        registrationFrame.add(new JLabel("Name:")).setFont(new Font("Arial",Font.BOLD,30));
        registrationFrame.add(nameField);
        registrationFrame.add(new JLabel("Email:")).setFont(new Font("Arial",Font.BOLD,30));
        registrationFrame.add(emailField);
        registrationFrame.add(new JLabel("Contact Number:")).setFont(new Font("Arial",Font.BOLD,30));
        registrationFrame.add(contactField);
        registrationFrame.add(registerButton);
        userIDField.setFont(new Font("Arial",Font.BOLD,30));
        nameField.setFont(new Font("Arial",Font.BOLD,30));
        emailField.setFont(new Font("Arial",Font.BOLD,30));
        contactField.setFont(new Font("Arial",Font.BOLD,30));
        registerButton.setFont(new Font("Arial",Font.BOLD,30));
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
                  //  JOptionPane.getRootFrame().setFont(new Font("Arial",Font.BOLD,30));
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
