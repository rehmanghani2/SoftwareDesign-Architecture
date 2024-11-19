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
public class MeritListFilter extends Observer {
     @Override
    public void update(String studentName, AdmissionProcess admissionProcess) {
        if ("Eligible".equals(admissionProcess.getCurrentStatus())) {
            System.out.println("TestFilter: Conducting test for " + studentName);
            admissionProcess.setCurrentStatus("Test Passed");
            System.out.println("TestFilter: " + studentName + " passed the test.");
        } else {
            System.out.println("TestFilter: Skipping test as " + studentName + " is ineligible.");
        }
    }
}
