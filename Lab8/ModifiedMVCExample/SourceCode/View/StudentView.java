/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Student;

/**
 *
 * @author Ghani
 */
public class StudentView {
    public void showStudentDetails(Student std){
        System.out.println("---------- STUDENT INFO -----------");
        System.out.println("Name: "+std.getName());
        System.out.println("Reg No: "+std.getRegNo());
        System.out.println("Semister: "+std.getSemister());
        System.out.println("GPA : "+std.getGpa());
    }
}
