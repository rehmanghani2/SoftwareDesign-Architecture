/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com;

/**
 *
 * @author Ghani
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import main.java.com.model.Car;
import main.java.com.view.BookingGUI;
import main.java.com.view.CarDetailsGUI;
import main.java.com.view.UserRegistrationGUI;

public class Main {
    private JFrame mainFrame;

    public Main() {
        // Initialize main frame
        mainFrame = new JFrame("Car Rental Application");
        mainFrame.setSize(600, 400);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout());
        
        JPanel headPanel = new JPanel(new BorderLayout());
        headPanel.setForeground(Color.GREEN);
        JLabel heading = new JLabel("Car Rental App");
        heading.setFont(new Font("Times New Roman",Font.BOLD,24));
        heading.setForeground(Color.BLUE);
        
        // Create a menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");
        
        
        
        
        // Create menu items
        JMenuItem registerUserItem = new JMenuItem("Register User");
        JMenuItem bookCarItem = new JMenuItem("Book a Car");
        JMenuItem viewCarsItem = new JMenuItem("View Cars");
        
        JPanel panel = new JPanel(new BorderLayout());

        //panel.setLayout();
        JButton registerBtn = new JButton("Register");
        registerBtn.setFont(new Font("Arial",Font.BOLD,24));
     //   registerBtn.setBackground(Color.yellow);
      //  registerBtn.setText("Register");
        
        JButton bookingBtn = new JButton("Book Car");
        bookingBtn.setFont(new Font("Arial",Font.BOLD,24));
      //  registerBtn.setBackground(Color.yellow);
       // registerBtn.setText("Book Car");
        
        JButton viewDetailsBtn = new JButton("View Car Details");
        viewDetailsBtn.setFont(new Font("Arial",Font.BOLD,24));
       // registerBtn.setBackground(Color.yellow);
        // registerBtn.setText("View Car Details");
        
        // Add action listeners
        registerUserItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserRegistrationGUI.showUserRegistrationFrame();
            }
        });
        
        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserRegistrationGUI.showUserRegistrationFrame();
            }
        });

        bookCarItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BookingGUI.showBookingFrame();
            }
        });
        
         bookingBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BookingGUI.showBookingFrame();
            }
        });

        viewCarsItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              //  CarDetailsGUI.showCarDetailsFrame();
              Car car=  new Car("c001");
              CarDetailsGUI cd=new CarDetailsGUI(car);
              cd.setVisible(true);
             // cd.carDetialsView(car);
            }
        });
        viewDetailsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // CarDetailsGUI.showCarDetailsFrame();
              Car car=  new Car("c001");
              CarDetailsGUI cd=new CarDetailsGUI(car);
              cd.setVisible(true);
            }
        });

        
        // Add heading 
       
        // Add menu items to menu
        menu.add(registerUserItem);
        menu.add(bookCarItem);
        menu.add(viewCarsItem);
        menuBar.add(menu);
        
        // add heading to top
//        headPanel.add(heading,BorderLayout.CENTER);
//        headPanel.setPreferredSize(new Dimension(100,100));
        
      //  panel.add(heading,BorderLayout.PAGE_START);
        panel.add(registerBtn,BorderLayout.NORTH);
        panel.add(bookingBtn,BorderLayout.CENTER);
        panel.add(viewDetailsBtn,BorderLayout.SOUTH);
        panel.setPreferredSize(new Dimension(100, 200));
        // Set the menu bar
       
        mainFrame.setJMenuBar(menuBar);
         mainFrame.getContentPane().add(headPanel);
       // mainFrame.add(heading);
        mainFrame.getContentPane().add(panel);
       // mainFrame.pack();
        // Show main frame
        mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main());
    }
}

