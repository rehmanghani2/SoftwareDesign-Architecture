/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Controller.StudentController;
import Model.Student;
import View.StudentView;

/**
 *
 * @author Ghani
 */
public class Main {
    public static void main(String[] args) {
    Student std1 =  new Student("Rehman Ghani","FA22-BSE-099",5,3.84);
    Student std2 =  new Student("Zain","FA22-BSE-069",5,3.00);
    Student std3 =  new Student("Usman","FA22-BSE-053",5,2.99);
    StudentView stdView = new StudentView();
    StudentController control = new StudentController(std1, stdView);
    StudentController control2 = new StudentController(std2, stdView);
    StudentController control3 = new StudentController(std3, stdView);
    
    control.setData(std1);
    control.updateStudentView();
    
    control2.setData(std2);
    control2.updateStudentView();
    
    control3.setData(std3);
    control3.updateStudentView();
    }    
}
