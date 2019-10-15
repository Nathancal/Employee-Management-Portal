/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belfastmet.P2A1.model;

import java.sql.Date;

/**
 *
 * @author macbookuser
 */
public class Employee {
    
    private int employeeID;
    private String firstName;
    private String lastName;
    private String accessLevel;
    private String address;
    private Date dob;
    private String email;
    private String contactNum;

    //Employee Constructor
    public Employee(int employeeID, String firstName, String lastName, String accessLevel, String address, Date dob, String email, String contactNum) {
        this.employeeID = employeeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.accessLevel = accessLevel;
        this.address = address;
        this.dob = dob;
        this.email = email;
        this.contactNum = contactNum;
    }

    //Getters for Employee
    public int getEmployeeID() {
        return employeeID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAccessLevel() {
        return accessLevel;
    }

    public String getAddress() {
        return address;
    }

    public Date getDob() {
        return dob;
    }

    public String getEmail() {
        return email;
    }

    public String getContactNum() {
        return contactNum;
    }

    @Override
    public String toString(){
        return this.employeeID + "," + firstName + "," + lastName + "," + accessLevel + "," + address +
                "," + dob + "," + email + "," + contactNum;
    
    }
    
    

    
    
    
}

