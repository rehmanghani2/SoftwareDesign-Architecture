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
public class EligibilityFilter implements Observer{


    @Override
    public void update(String studentName, AdmissionProcess admissionProcess) {
        System.out.println("EligibilityFilter: Checking eligibility for " + studentName);
        // Simple logic: Pass if the student's name length > 3
        if (studentName.length() > 3) {
            admissionProcess.setCurrentStatus("Eligible");
            System.out.println("EligibilityFilter: " + studentName + " is eligible.");
        } else {
            admissionProcess.setCurrentStatus("Ineligible");
            System.out.println("EligibilityFilter: " + studentName + " is ineligible.");
        }
    }
}
