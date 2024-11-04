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
public class PaymentDetails {
    private String payment_id;
    private String paymentMethod;       // e.g., Credit Card, Debit Card, PayPal
    private String cardNumber;
    private String cardHolderName;
    private String expiryDate;          // Format: MM/YY
    private String cvv;
    private String billingAddress;
    private String amount;

    // Constructor
    public PaymentDetails(String paymentMethod, String cardNumber, String cardHolderName, 
                          String expiryDate, String cvv, String billingAddress) {
        this.paymentMethod = paymentMethod;
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
        this.billingAddress = billingAddress;
    }
    public PaymentDetails(String payment_id,String paymentMethod, String cardNumber, String cardHolderName, String amount) {
        this.payment_id = payment_id;
        this.paymentMethod = paymentMethod;
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
//        this.expiryDate = expiryDate;
//        this.cvv = cvv;
//        this.billingAddress = billingAddress;
//        this.amount = amount;
    }
    public PaymentDetails(){
        this.payment_id = payment_id;
        this.paymentMethod = paymentMethod;
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
        this.billingAddress = billingAddress;
        this.amount = amount;
    }

    // Getters and Setters
    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }
    public String getPaymentInfoID(){
        return this.payment_id;
    }
    public void setPaymentInfoID(String id){
        this.payment_id = id;
    }
    public String getAmount(){
        return this.amount;
    }
    public void setAmount(String amount){
        this.amount = amount;
    }
    

    @Override
    public String toString() {
        return "PaymentDetails{" +
                "paymentMethod='" + paymentMethod + '\'' +
                ", cardHolderName='" + cardHolderName + '\'' +
                ", billingAddress='" + billingAddress + '\'' +
                '}';
    }
}
