/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

/**
 *
 * @author Ghani
 */
public class AdmissionProcess extends Subject {
   private List<Observer> observers = new ArrayList<>();
    private String currentStatus = ""; // Tracks the status of the student

    // Add an observer to the list
  //  @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    // Remove an observer from the list
  //  @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    // Notify all observers
   // @Override
    public void notifyObserver(String studentName) {
        for (Observer observer : observers) {
            observer.update(studentName, this);
        }
    }

    // Getter and setter for current status
    public void setCurrentStatus(String status) {
        this.currentStatus = status;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    // Trigger the admission process
    public void processApplication(String studentName) {
        setCurrentStatus("Application started");
        notifyObservers(studentName);
    }
}
