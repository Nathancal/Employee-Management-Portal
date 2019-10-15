/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belfastmet.P2A1.db;

import belfastmet.P2A1.mapper.ScheduleMapper;
import belfastmet.P2A1.model.Employee;
import belfastmet.P2A1.model.Schedule;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;


/**
 *
 * @author macbookuser
 */
public class ScheduleCrud {
    
    //DB Connection
    private static final String DB_URL = "jdbc:ucanaccess://P2A1DB.accdb;";
    
    //Creates a ScheduleMapper object called mapper.
    private ScheduleMapper mapper;
    
    public ScheduleCrud(ScheduleMapper mapper){
        this.mapper = mapper;
    
    }
    
    public void create(Schedule schedule){
        //Create statement that allows for the creation of a new shift in the database.
        String sql = "INSERT INTO Schedule(EmployeeID, TimeSlot, Shift) VALUES (?,?,?)";
        try{
            
            Connection connection = DriverManager.getConnection(DB_URL);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
            //calls the methods that inserts the collected schedule info 
            preparedStatement.setInt(1, schedule.getEmployeeID());                        
            preparedStatement.setDate(2, schedule.getTimeSlot());
            preparedStatement.setString(3, schedule.getShift());
                        
            //Executes the SQL
            preparedStatement.execute();
            
            connection.close();       
        }
        catch(SQLException ex){
            ex.printStackTrace();
        
        }
    }
        
//        public void update(Schedule schedule){
//            String sql ="UPDATE Schedule SET EmployeeID = ?, TimeSlot = ?";
//            
//            try{
//            
//            Connection connection = DriverManager.getConnection(DB_URL);
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//             
//            preparedStatement.setInt(1, schedule.getEmployeeID());                        
//            preparedStatement.setDate(1, schedule.getTimeSlot());
//            
//            preparedStatement.execute();
//            
//            connection.close();       
//            }
//            catch(SQLException ex){
//            ex.printStackTrace();
//        
//            }
//             
//        }
        
        public void delete(Schedule schedule){
        //Method to delete entries from the schedule
        String sql = "DELETE FROM Schedule WHERE EmployeeID = ? AND TimeSlot = ? AND Shift = ?";
        
            try{
            
                Connection connection = DriverManager.getConnection(DB_URL);
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
            
                preparedStatement.setInt(1, schedule.getEmployeeID()); 
                preparedStatement.setDate(2, schedule.getTimeSlot());
                preparedStatement.setString(3, schedule.getShift());  

                preparedStatement.execute();
            
                connection.close();                 
            }
            catch(SQLException ex){
                ex.printStackTrace();      
            }                  
        }
        
        //Method to display individual entries in the schedule
        public Schedule read(int employeeID, Date timeSlot, String shift){
        String sql = "SELECT * FROM Schedule WHERE EmployeeID = ? AND TimeSlot = ? AND Shift = ?";
            try{
            
                Connection connection = DriverManager.getConnection(DB_URL);
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                
                preparedStatement.setInt(1, employeeID);
                preparedStatement.setDate(2, timeSlot);
                preparedStatement.setString(3, shift);

                ResultSet rs = preparedStatement.executeQuery();

                System.out.println("Got Here!");

                Schedule schedule = mapper.map(rs);

                connection.close();

                
                return schedule;
            
        
            }
            catch(SQLException ex){
                ex.printStackTrace();
        
            }
       
            return null;         
        }
      
        
        //Method to output all the entries in the schedule into the table
        public Schedule[] readAll(){
            //Selects the shifts for the schedule table ordering them by timeslot first then by shift
            String sql = "SELECT * FROM Schedule ORDER BY TimeSlot, Shift";
            try{
            
                Connection connection = DriverManager.getConnection(DB_URL);
                PreparedStatement preparedStatement = connection.prepareStatement(sql);


                ResultSet rs = preparedStatement.executeQuery();

                System.out.println("Got Here!");

                Schedule[] schedule = mapper.mapAll(rs);

                connection.close();

                return schedule;


            }
            catch(SQLException ex){
                ex.printStackTrace();

            }
            
            return null;
            
        
        
        }

    //
    public Schedule[] readAllForUser(int employeeID) {
        String sql = "SELECT * FROM Schedule WHERE EmployeeID = ? ORDER BY TimeSlot, Shift ";
            try{
            
                Connection connection = DriverManager.getConnection(DB_URL);
                PreparedStatement preparedStatement = connection.prepareStatement(sql);

                preparedStatement.setInt(1, employeeID);


                ResultSet rs = preparedStatement.executeQuery();
                

                System.out.println("Got Here!");

                Schedule[] schedule = mapper.mapAll(rs);

                connection.close();

                return schedule;

            }
            catch(SQLException ex){
                ex.printStackTrace();

            }
            
            return null;
    }
    
    
    }
    
    

