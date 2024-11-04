
package main.java.com.model;

import java.util.Date;

/**
 *
 * @author Ghani
 */
import java.sql.*;
import java.util.Date;

public class Booking {
    private String bookingID;
    private String carID;
    private String userID;
    private Date startDate;
    private Date endDate;
    private String status;

    // Database connection variables
    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/carrentalapp";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "@hm@dz@i123";

    // Constructor for creating a new booking
    public Booking(String carID, String userID, Date startDate, Date endDate) {
        this.carID = carID;
        this.userID = userID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = "Pending";
        this.bookingID = generateBookingID();
        saveBookingToDatabase();
    }

    // Constructor for retrieving a booking with known parameters
    public Booking(String bookingID, String carID, String userID, Date startDate, Date endDate, String status) {
        this.bookingID = bookingID;
        this.carID = carID;
        this.userID = userID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    // Generate a mock booking ID
    private String generateBookingID() {
        return "BOOK" + System.currentTimeMillis();
    }

    // Save the booking to the database
    private void saveBookingToDatabase() {
        String sql = "INSERT INTO bookings (bookingID, carID, userID, startDate, endDate, status) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, bookingID);
            pstmt.setString(2, carID);
            pstmt.setString(3, userID);
            pstmt.setDate(4, new java.sql.Date(startDate.getTime()));
            pstmt.setDate(5, new java.sql.Date(endDate.getTime()));
            pstmt.setString(6, status);

            pstmt.executeUpdate();
            System.out.println("Booking saved to the database with ID: " + bookingID);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to save booking to the database.");
        }
    }

    // Update booking status and update the database
    public void updateBookingStatus(String status) {
        this.status = status;
        updateBookingStatusInDatabase();
    }

    private void updateBookingStatusInDatabase() {
        String sql = "UPDATE bookings SET status = ? WHERE bookingID = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, status);
            pstmt.setString(2, bookingID);
            pstmt.executeUpdate();
            System.out.println("Booking status updated in the database for Booking ID: " + bookingID);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to update booking status in the database.");
        }
    }

    // Retrieve booking details
    public BookingDetails getBookingDetails() {
        return new BookingDetails(bookingID, carID, userID, startDate, endDate, status);
    }

    // Static method to retrieve a booking by ID from the database
    public static Booking getBookingByID(String bookingID) {
        String sql = "SELECT * FROM bookings WHERE bookingID = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, bookingID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String carID = rs.getString("carID");
                String userID = rs.getString("userID");
                Date startDate = rs.getDate("startDate");
                Date endDate = rs.getDate("endDate");
                String status = rs.getString("status");

                return new Booking(bookingID, carID, userID, startDate, endDate, status);
            } else {
                System.out.println("Booking not found with ID: " + bookingID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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




//public class Booking {
//    private String bookingID;
//    private String carID;
//    private String userID;
//    private Date startDate;
//    private Date endDate;
//    private String status;
//
//    public Booking(String carID, String userID, Date startDate, Date endDate) {
//        this.carID = carID;
//        this.userID = userID;
//        this.startDate = startDate;
//        this.endDate = endDate;
//        this.status = "Pending";
//        this.bookingID = generateBookingID();
//        // Simulate saving booking to the database
//        saveBookingToDatabase();
//    }
//
//    // Generate a mock booking ID
//    private String generateBookingID() {
//        return "BOOK" + System.currentTimeMillis();
//    }
//
//    // Save the booking to the database (simulation)
//    private void saveBookingToDatabase() {
//        System.out.println("Saving booking to the database with ID: " + bookingID);
//        // Code to insert the booking data into the database can go here
//    }
//
//    // Update the booking status
//    public void updateBookingStatus(String status) {
//        this.status = status;
//        System.out.println("Booking status updated to: " + status);
//        // Update status in the database
//        updateBookingStatusInDatabase();
//    }
//
//    // Update the booking status in the database (simulation)
//    private void updateBookingStatusInDatabase() {
//        System.out.println("Updating booking status in the database for Booking ID: " + bookingID);
//        // Code to update the booking status in the database can go here
//        
//    }
//
//    // Get the details of the booking
//    public BookingDetails getBookingDetails() {
//        return new BookingDetails(bookingID, carID, userID, startDate, endDate, status);
//    }
//     // Constructor
//    public Booking(String bookingID, String carID, String userID, Date startDate, Date endDate, String status) {
//        this.bookingID = bookingID;
//        this.carID = carID;
//        this.userID = userID;
//        this.startDate = startDate;
//        this.endDate = endDate;
//        this.status = status;
//    }
//
//    // Getters and Setters
//    public String getBookingID() {
//        return bookingID;
//    }
//
//    public void setBookingID(String bookingID) {
//        this.bookingID = bookingID;
//    }
//
//    public String getCarID() {
//        return carID;
//    }
//
//    public void setCarID(String carID) {
//        this.carID = carID;
//    }
//
//    public String getUserID() {
//        return userID;
//    }
//
//    public void setUserID(String userID) {
//        this.userID = userID;
//    }
//
//    public Date getStartDate() {
//        return startDate;
//    }
//
//    public void setStartDate(Date startDate) {
//        this.startDate = startDate;
//    }
//
//    public Date getEndDate() {
//        return endDate;
//    }
//
//    public void setEndDate(Date endDate) {
//        this.endDate = endDate;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    @Override
//    public String toString() {
//        return "BookingDetails{" +
//                "bookingID='" + bookingID + '\'' +
//                ", carID='" + carID + '\'' +
//                ", userID='" + userID + '\'' +
//                ", startDate=" + startDate +
//                ", endDate=" + endDate +
//                ", status='" + status + '\'' +
//                '}';
//    }
//}