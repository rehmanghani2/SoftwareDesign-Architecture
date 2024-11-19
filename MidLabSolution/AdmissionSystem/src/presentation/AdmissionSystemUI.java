/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import Business.AdmissionService;

/**
 *
 * @author Ghani
 */
public class AdmissionSystemUI {
    public static void main(String[] args) {
        AdmissionService service = new AdmissionService();

        // Simulate two students with different eligibility
        System.out.println("Processing Student 1:");
        service.startProcess("Rehman Ghani");

        System.out.println("\nProcessing Student 2:");
        service.startProcess("ALi");
    }
}
