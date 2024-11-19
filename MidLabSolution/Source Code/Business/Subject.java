/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

/**
 *
 * @author Ghani
 */
public abstract class Subject {
   public abstract void addObserver(Observer observer);
   public abstract void removeObserver(Observer observer);
   public abstract void notifyObserver(String message);
}
