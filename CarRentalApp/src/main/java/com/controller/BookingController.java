/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.controller;

import java.util.Date;
import main.java.com.model.Car;
import main.java.com.model.PaymentDetails;
import main.java.com.model.RentalAgreement;
import main.java.com.model.User;
import main.java.com.model.Car;
import main.java.com.model.User;
/**
 *
 * @author Ghani
 */
public class BookingController {
    private String bookingID;

//    public void bookCar(String carID, Date startDate, Date endDate, String userID) {
//        Car car = new Car(carID);
//        User user = new User(userID);
//        
//        if (car.checkAvailability(startDate, endDate)) {
//            PaymentProcessor paymentProcessor = new PaymentProcessor();
//            if (paymentProcessor.processPayment(user.getPaymentInfo())) {
//                RentalAgreement agreement = createRentalAgreement(carID, userID, startDate, endDate);
//                NotificationService notificationService = new NotificationService();
//                notificationService.sendInAppNotification(userID, "Booking confirmed.");
//                updateCarStatus(carID, "Booked", startDate, endDate);
//            }
//        }
//    }
    
    public Boolean bookCar(String carID, Date startDate, Date endDate, String userID) {
        if(carID.equals("") || startDate.equals("") || endDate.equals("") || userID.equals("")){
            return false;
        }
        Car car = new Car(carID);
        User user = new User(userID);
        boolean flag=false;
        if (car.checkAvailability(startDate, endDate)) {
            PaymentProcessor paymentProcessor = new PaymentProcessor("Credit");
            if (paymentProcessor.processPayment(user.getPaymentInfo())) {
                RentalAgreement agreement = createRentalAgreement(carID, userID, startDate, endDate);
                NotificationService notificationService = new NotificationService();
                notificationService.sendInAppNotification(userID, "Booking confirmed.");
                updateCarStatus(carID, "Booked", startDate, endDate);
                flag=true;
            }
        }
        return flag;
    }

    public boolean checkAvailability(String carID, Date startDate, Date endDate) {
        Car car = new Car(carID);
        return car.checkAvailability(startDate, endDate);
    }

    public boolean processPayment(PaymentDetails paymentDetails) {
        PaymentProcessor paymentProcessor = new PaymentProcessor();
        return paymentProcessor.processPayment(paymentDetails);
    }

    public RentalAgreement createRentalAgreement(String carID, String userID, Date startDate, Date endDate) {
        return new RentalAgreement(carID, userID, startDate, endDate);
    }

    public String  sendNotification(String userID, String message) {
        NotificationService notificationService = new NotificationService();
        return notificationService.sendInAppNotification(userID, message);
    }

    public void updateCarStatus(String carID, String status, Date startDate, Date endDate) {
        Car car = new Car(carID);
        car.updateStatus(status, startDate, endDate);
    }
}
