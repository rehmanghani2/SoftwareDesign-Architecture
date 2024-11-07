/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.model;

/**
 *
 * @author Ghani
 */
import java.util.Date;

public class BookingDetails {
    private String bookingID;
    private String carID;
    private String userID;
    private Date startDate;
    private Date endDate;
    private String status;

    // Constructor
    public BookingDetails(String bookingID, String carID, String userID, Date startDate, Date endDate, String status) {
        this.bookingID = bookingID;
        this.carID = carID;
        this.userID = userID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    // Getters and Setters
    public String getBookingID() {
        return bookingID;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BookingDetails{" +
                "bookingID='" + bookingID + '\'' +
                ", carID='" + carID + '\'' +
                ", userID='" + userID + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status='" + status + '\'' +
                '}';
    }
}

