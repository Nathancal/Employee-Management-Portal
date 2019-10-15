/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package belfastmet.P2A1.mapper;

import belfastmet.P2A1.model.Schedule;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author macbookuser
 */
public class ScheduleMapper {
    
    //Creates an array of Schedules for mapping across the objects
    public Schedule[] mapAll(ResultSet rs) throws SQLException{
        System.out.println("TRACE - in mapAll");
    
    //Creates a list populated by instances of schedule
    List<Schedule> schedules = new ArrayList();
    
    
        while(rs.next()){
        
            int employeeID = rs.getInt("EmployeeID");
            Date timeSlot = rs.getDate("TimeSlot");
            String shift = rs.getString("Shift");

            
            System.out.println("TRACE - Timeslot: " + timeSlot);
        
            Schedule schedule = new Schedule(employeeID, timeSlot, shift);
            schedules.add(schedule);
        
        }
        
        return schedules.toArray(new Schedule[schedules.size()]);

    }
    
    //Creates an instance of shcedule for the mapper
    public Schedule map(ResultSet rs) throws SQLException{
        rs.next();
        
        int employeeID = rs.getInt("EmployeeID");
        Date timeSlot = rs.getDate("TimeSlot");
        String shift = rs.getString("Shift");
        
        Schedule schedule = new Schedule(employeeID, timeSlot, shift);
        return schedule;
    
    
    }
    
    
}
