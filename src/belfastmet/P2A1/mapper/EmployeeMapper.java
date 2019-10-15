/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belfastmet.P2A1.mapper;

import belfastmet.P2A1.model.Employee;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author macbookuser
 */
public class EmployeeMapper {
    
    //Maps the employee information from the database and lists 
    //it in an array for it to be displayed in the table.
    public Employee[] mapAll(ResultSet rs) throws SQLException{
        
        //Creates a List of instances of employee
        List<Employee> employees = new ArrayList();
        
        //Does this until all records are in the list
        while(rs.next()){
           
           //Retrieves the data from the dB
           int employeeID = rs.getInt("EmployeeID");
           String firstName = rs.getString("FirstName");
           String lastName = rs.getString("LastName");
           String accessLevel = rs.getString("AccessLevel");
           String address = rs.getString("Address");
           Date dob = rs.getDate("DOB");
           String email = rs.getString("Email");
           String contactNum = rs.getString("ContactNum");
           
           Employee employee = new Employee(employeeID, firstName, lastName, accessLevel, address, dob, email, contactNum);
           employees.add(employee); 
        }
    
        //Returns the data to an array which is the size of the list 
        return employees.toArray(new Employee[employees.size()]);
    }
    
    //Maps the employee information for an invidual instance of employee
    public Employee map(ResultSet rs) throws SQLException{
        rs.next();
        
        int employeeID = rs.getInt("EmployeeID");
           String firstName = rs.getString("FirstName");
           String lastName = rs.getString("LastName");
           String accessLevel = rs.getString("AccessLevel");
           String address = rs.getString("Address");
           Date dob = rs.getDate("DOB");
           String email = rs.getString("Email");
           String contactNum = rs.getString("ContactNum");
           
           
           Employee employee = new Employee(employeeID, firstName, lastName, accessLevel, address, dob, email, contactNum);
           return employee; 
    
    }
    
}
