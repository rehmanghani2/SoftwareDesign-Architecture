/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Student;
import View.StudentView;

/**
 *
 * @author Ghani
 */
public class StudentController {
    Student student ;// = new Student("Rehman Ghani","FA22-BSE-099",5,3.84);
    StudentView stdView;// = new StudentView();
    public StudentController(Student student, StudentView stdView){
        this.stdView = stdView;
        this.student = student;
    }
    public void setData(Student std){
       Student stud = new Student(student.getName(),student.getRegNo(),student.getSemister(),student.getGpa());
    }
    public Student getData(){
       return student;
    }
    public void updateStudentView(){
        stdView.showStudentDetails(student);
    }
    
}
