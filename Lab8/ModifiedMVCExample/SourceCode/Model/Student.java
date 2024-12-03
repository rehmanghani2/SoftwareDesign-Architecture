/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


public class Student {
   private String name;
   private String regNo;
   private int semister;
   private double gpa;
   public Student(String name, String regNo, int semister, double gpa){
       this.name = name;
       this.semister = semister;
       this.regNo = regNo;
       this.gpa = gpa;
   }
   public void setName(String name){
       this.name = name;
   }
   public void setRegNo(String regNo){
       this.regNo = regNo;
   }
   public void setSemister(int semister){
       this.semister = semister;
   }
   public void gpa(double gpa){
       this.gpa = gpa;
   }
   
   public String getName(){
       return name;
   }
   public String getRegNo(){
       return regNo;
   }
   public int getSemister(){
       return semister;
   }
   public double getGpa(){
       return gpa;
   }
   
}
