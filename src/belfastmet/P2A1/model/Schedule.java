/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belfastmet.P2A1.model;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author macbookuser
 */
public class Schedule {
    
    private int employeeID;
    private Date timeSlot;
    private String shift;
    
    //Schedule constructor
    public Schedule(int employeeID, Date timeSlot, String shift){
        this.employeeID = employeeID;
        this.timeSlot = timeSlot;  
        this.shift = shift;
    
    }

    //Getters for Schedule
    public int getEmployeeID() {
        return employeeID;
    }

    public Date getTimeSlot() {
        return timeSlot;
    }

    public String getShift() {
        return shift;
    }
    
    
    public String toString(){
        return this.employeeID + "," + this.timeSlot + "," + this.shift;
    
    }
    
    
}
