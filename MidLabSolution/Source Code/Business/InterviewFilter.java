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
public class InterviewFilter extends Observer{
     @Override
    public void update(String studentName, AdmissionProcess admissionProcess) {
        if ("Test Passed".equals(admissionProcess.getCurrentStatus())) {
            System.out.println("InterviewFilter: Conducting interview for " + studentName);
            admissionProcess.setCurrentStatus("Interview Passed");
            System.out.println("InterviewFilter: " + studentName + " passed the interview.");
        } else {
            System.out.println("InterviewFilter: Skipping interview for " + studentName);
        }
    }
}
