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

public class AgreementDetails {
    private String agreementID;
    private String carID;
    private String userID;
    private Date startDate;
    private Date endDate;
    private double totalCost;

    // Constructor
    public AgreementDetails(String agreementID, String carID, String userID, Date startDate, Date endDate, double totalCost) {
        this.agreementID = agreementID;
        this.carID = carID;
        this.userID = userID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalCost = totalCost;
    }

    // Getters and Setters
    public String getAgreementID() {
        return agreementID;
    }

    public void setAgreementID(String agreementID) {
        this.agreementID = agreementID;
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

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public String toString() {
        return "AgreementDetails{" +
                "agreementID='" + agreementID + '\'' +
                ", carID='" + carID + '\'' +
                ", userID='" + userID + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", totalCost=" + totalCost +
                '}';
    }
}
