/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jframes;

import belfastmet.P2A1.db.EmployeeCrud;
import belfastmet.P2A1.db.ScheduleCrud;
import belfastmet.P2A1.model.Employee;
import belfastmet.P2A1.model.Schedule;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author macbookuser
 */
public class ScheduleFrame extends JPanel {
    
    private static final String[] columnNames = {"EmployeeID","Date","Shift"};
    
    private ScheduleCrud scheduleCrud;
    private EmployeeCrud employeeCrud;
    private Schedule selected;
    private ScheduleLauncher scheduleLauncher;
    private Employee user;
    
    Object[][] data;
    JTable table;
    
    public ScheduleFrame(ScheduleCrud scheduleCrud, Employee user){
        //Creates a jpanel that contains the table for the schedule
        super(new GridLayout(1,0));
        this.scheduleCrud = scheduleCrud;   
        this.user = user;
        
        refresh();
    }
    
    //A multidimensional array that structures the data to be displayed in the 
    //Schedule table
    public Object[][] getData(){
        System.out.println("TRACE - In Get Data");
        Schedule[] schedules;
        
        //Checks if the access level is a manager. 
        //Allows the manager to access all schedule records
        //employees are only able to access their own records.
        if("Manager".equals(user.getAccessLevel())){
            schedules = scheduleCrud.readAll(); 
        } else {
                    
            schedules = scheduleCrud.readAllForUser(user.getEmployeeID());
            //readAllForUser(user.getEmployeeID) - this allows the user to access
            //the schedule information applicabale to their own EmployeeID
        }
        
        
        
        List<Schedule> scheduleList = Arrays.asList(schedules);
        
        Object[][] returnObjectArray = new Object[scheduleList.size()][3];
        
        int i = 0;
        
        for(Schedule schedule: scheduleList){
            returnObjectArray[i][0] = schedule.getEmployeeID();
            returnObjectArray[i][1] = schedule.getTimeSlot(); 
            returnObjectArray[i][2] = schedule.getShift();
            i++;
        }
        
        return returnObjectArray;
    
    }
    
    public Schedule getSelected(){
        return this.selected;
    
    }

    
    public void refresh(){
        this.selected = null;
        this.removeAll();
        data = getData();
        
        table = new JTable(data, columnNames);
        table.setModel(new DefaultTableModel(data, columnNames){
            
        @Override
            public boolean isCellEditable(int row, int column){
                return false;
    
            }
        });  
        table.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                JTable table = (JTable) e.getSource();
                int employeeID = (Integer)table.getValueAt(table.getSelectedRow(), 0);
                Date date = (Date)table.getValueAt(table.getSelectedRow(), 1);
                String shift = (String)table.getValueAt(table.getSelectedRow(), 2);
                selected = new Schedule(employeeID, date, shift);
                table.clearSelection();
                System.out.println("Selected: " + selected);
            }
        
        
        });
        
        table.setPreferredScrollableViewportSize(new Dimension (500,70));
        table.setFillsViewportHeight(true);
        
        JScrollPane scrollPane = new JScrollPane(table);
        
        add(scrollPane);
    }

    public ScheduleLauncher getScheduleLauncher() {
        return scheduleLauncher;
    }

    public void setScheduleLauncher(ScheduleLauncher scheduleLauncher) {
        this.scheduleLauncher = scheduleLauncher;
    }
    
    
    
}
