/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jframes;

import belfastmet.P2A1.db.ScheduleCrud;
import belfastmet.P2A1.mapper.ScheduleMapper;
import belfastmet.P2A1.model.Employee;
import belfastmet.P2A1.model.Schedule;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.sql.Timestamp;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author macbookuser
 */
public class ScheduleLauncher extends JFrame {
    
    private ScheduleActions scheduleActions;
    private ScheduleForm scheduleForm;
    private ScheduleFrame scheduleFrame;
    private Employee user;
    
    private ScheduleCrud scheduleCrud;
    
    private MainFrame mainFrame;

    public ScheduleLauncher(Employee user){
        this.user = user;
    
    }
    
    public void setup(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridLayout gridLayout = new GridLayout(1,2);               
        this.setLayout(gridLayout);

        this.setupScheduleCrud();

        this.setupScheduleFrame();
        this.setupScheduleActions();
        this.setupScheduleForm();
        
        JButton backButton = new JButton("Back"){
                    @Override
                    protected void fireActionPerformed(ActionEvent event) {
                        close();
                    }
                };
                
                //TODO change look of the back button
                
                this.add(backButton);

        this.pack();
        this.setVisible(true);
    }
        
        private void setupScheduleCrud(){
            this.scheduleCrud = new ScheduleCrud(new ScheduleMapper());
        
        }
        
        private void setupScheduleActions(){
            if("Manager".equals(user.getAccessLevel())){
                scheduleActions = new ScheduleActions(user);
                scheduleActions.setOpaque(true);
                this.add(scheduleActions);
                scheduleActions.setScheduleLauncher(this);
            } 
        }
        
        private void setupScheduleForm(){
            if("Manager".equals(user.getAccessLevel())){
                scheduleForm = new ScheduleForm(scheduleCrud, user);
                scheduleForm.setOpaque(true);
                this.add(scheduleForm);
                scheduleForm.setScheduleLauncher(this);
            }
        }
        
        private void setupScheduleFrame(){
            scheduleFrame = new ScheduleFrame(scheduleCrud, user);
            scheduleFrame.setOpaque(true);
            this.add(scheduleFrame);
            scheduleFrame.setScheduleLauncher(this);
        }
        
        public void create(){
            scheduleForm.createSchedule();
        
        }
        
        public void delete(){
            int employeeID = scheduleFrame.getSelected().getEmployeeID();
            Date timeSlot = scheduleFrame.getSelected().getTimeSlot();
            String shift = scheduleFrame.getSelected().getShift();
            
            Schedule scheduleToDelete = new Schedule(employeeID, timeSlot, shift);
            
            scheduleToDelete.equals(scheduleFrame.getSelected());
            
            scheduleCrud.delete(scheduleToDelete);
            refresh();
        }
        
        public void refresh(){
            scheduleFrame.refresh();
        }

        public void setMainFrame(MainFrame mainFrame){
            this.mainFrame = mainFrame;
        }
        
        public void close(){
            this.setVisible(false);
            mainFrame.setVisible(true);
        }
        
        
    }
    
    
