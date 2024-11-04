/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.controller;

/**
 *
 * @author Ghani
 */
public class NotificationService {
    private String notificationID;
    private String notificationType;

    // Send an email notification to the user
    public String sendEmailNotification(String email, String message) {
        // Simulate sending email
        System.out.println("Sending email to " + email + " with message: " + message);
        logNotification("Email");
        return "Sending email to " + email + " with message: " + message;
    }

    // Send an in-app notification to the user
    public String sendInAppNotification(String userID, String message) {
        // Simulate sending an in-app notification
        System.out.println("Sending in-app notification to User ID: " + userID + " with message: " + message);
        logNotification("In-App");
        return "Sending in-app notification to User ID: " + userID + " with message: " + message;

    }

    // Log notification details
    public String logNotification(String notificationType) {
        this.notificationID = "NOTIF" + System.currentTimeMillis();
        this.notificationType = notificationType;
        System.out.println("Notification logged with ID: " + notificationID + " and Type: " + notificationType);
        return "Notification logged with ID: " + notificationID + " and Type: " + notificationType;
    }
}
