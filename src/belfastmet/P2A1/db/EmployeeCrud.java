/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belfastmet.P2A1.db;

import belfastmet.P2A1.mapper.EmployeeMapper;
import belfastmet.P2A1.model.Employee;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author macbookuser
 */
public class EmployeeCrud {
    
    //as the location of the DB file is constant then it can be declared as a variable.
    private static final String DB_URL = "jdbc:ucanaccess://P2A1DB.accdb;";
    
    
    //Creates a new instane of the object EmployeeMapper called mapper.
    private EmployeeMapper mapper;

    //Creates a constructor for EmployeeCrud that contains EmployeeMapper as a parameter.
    public EmployeeCrud(EmployeeMapper mapper) {
        this.mapper = mapper;
    }
    
    public void create(Employee employee){
        //Create Statement that allows the user to create a new employee in the system.
        String sql = "INSERT INTO Employee (FirstName, LastName, AccessLevel, Address, DOB, Email, ContactNum) VALUES (?,?,?,?,?,?,?)";
        
        try{
            //Establishes the connection to the database using the DB_URL String followed by
            //the sql statement to be executed.
            Connection connection = DriverManager.getConnection(DB_URL);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            //retrieves the values from the Employee class using methods that have been collected using the GUI
            //and applies them to the DB.
            preparedStatement.setString(1, employee.getFirstName());                        
            preparedStatement.setString(2, employee.getLastName());                        
            preparedStatement.setString(3, employee.getAccessLevel());                        
            preparedStatement.setString(4, employee.getAddress());                        
            preparedStatement.setDate(5, employee.getDob());                        
            preparedStatement.setString(6, employee.getEmail());                        
            preparedStatement.setString(7, employee.getContactNum());                        
            
            //Executes the SQL statement.
            preparedStatement.execute();
            
            //Closes the connection to the database after it is completed.
            connection.close();       
        }
        catch(SQLException ex){
            ex.printStackTrace();
        
        }
        
    }
    
    public void update(Employee employee){
        //Update Statement that allows the user to update existing employee records.
        String sql = "UPDATE Employee SET FirstName = ?, LastName = ?, AccessLevel = ?, Address = ?, DOB = ?, Email = ?, ContactNum = ? WHERE EmployeeID = ?";
        
        try{
            
            //Connects to the database
            Connection connection = DriverManager.getConnection(DB_URL);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            //applies the methods to retrieve the data
            preparedStatement.setString(1, employee.getFirstName());                        
            preparedStatement.setString(2, employee.getLastName());                        
            preparedStatement.setString(3, employee.getAccessLevel());                        
            preparedStatement.setString(4, employee.getAddress());                        
            preparedStatement.setDate(5, employee.getDob());                        
            preparedStatement.setString(6, employee.getEmail());                        
            preparedStatement.setString(7, employee.getContactNum());  
            preparedStatement.setInt(8, employee.getEmployeeID());  

            //executes the SQL
            preparedStatement.execute();
            
            //closes the connection to the database when it is no longer being used.
            connection.close();       
        }
        catch(SQLException ex){
            ex.printStackTrace();
        
        }
        
    }
    
    public void delete(Employee employee){
        //Deletes the record for an employee based on an employee id.
        String sql = "DELETE FROM Employee WHERE EmployeeID = ?";
        
        try{
            
            Connection connection = DriverManager.getConnection(DB_URL);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            //Only one method used to retrieve as only employee id is needed to delete a record.
            preparedStatement.setInt(1, employee.getEmployeeID());                        
            
            preparedStatement.execute();
            
            connection.close();

            
        
        }
        catch(SQLException ex){
            ex.printStackTrace();
        
        }
    
    
    }
    
    public Employee read(int employeeID){
    
        //This statement allows the user to read information from the database
        String sql = "SELECT * FROM Employee WHERE EmployeeID = ?";
        try{
            
            //Connects to the database
            Connection connection = DriverManager.getConnection(DB_URL);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, employeeID);
                                     
            ResultSet rs = preparedStatement.executeQuery();
            
            System.out.println("Got Here!");
            
            //Creates a new instance of employee and sets its values to those 
            //Retrieved by the mapper.
            Employee employee = mapper.map(rs);
                  
            connection.close();
            
            //The details contained in the object employee are returned.
            return employee;
            
        
        }
        catch(SQLException ex){
            ex.printStackTrace();
        
        }
       
        return null;
    
    
    }
    
    
    public Employee[] readAll(){
        //SQL
        String sql = "SELECT * FROM Employee";
        try{
            //DB Connection
            Connection connection = DriverManager.getConnection(DB_URL);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
             
            ResultSet rs = preparedStatement.executeQuery();
            
            System.out.println("Got Here!");
            
            //Populates an array of employees using the mapAll method with the values of the result
            //set passed as a parameter.
            Employee[] employees = mapper.mapAll(rs);
                  
            //Close DB
            connection.close();
            
            //Returns the value of the array employees.
            return employees;
            
        
        }
        catch(SQLException ex){
            ex.printStackTrace();
        
        }
       
        return null;
    }

    //Creates a read all method that allows non management users to only see
    //Records that apply to themselves.
    public Employee[] readAllForUser(int employeeID) {
        String sql = "SELECT * FROM Employee WHERE EmployeeID = ?";
        try{
                       
            Connection connection = DriverManager.getConnection(DB_URL);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            preparedStatement.setInt(1, employeeID);
             
            ResultSet rs = preparedStatement.executeQuery();
            
            System.out.println("Got Here!");
            
            
            Employee[] employees = mapper.mapAll(rs);
                  
            connection.close();

            return employees;
            
        
        }
        catch(SQLException ex){
            ex.printStackTrace();
        
        }
        
        return null;
    }
    
    
}
