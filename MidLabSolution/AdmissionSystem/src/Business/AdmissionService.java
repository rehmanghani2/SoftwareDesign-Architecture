/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.util.Observer;

/**
 *
 * @author Ghani
 */
public class AdmissionService {
    private AdmissionProcess admissionProcess;

    public AdmissionService() {
        admissionProcess = new AdmissionProcess();

        // Register filters in order
        admissionProcess.addObserver(new EligibilityFilter());
        admissionProcess.addObserver(new TestFilter());
        admissionProcess.addObserver(new InterviewFilter());
        admissionProcess.addObserver( new MeritListFilter());
    }

    public void startProcess(String studentName) {
        admissionProcess.processApplication(studentName);
    }
}
