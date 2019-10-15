/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jframes;

import belfastmet.P2A1.db.EmployeeCrud;
import belfastmet.P2A1.mapper.EmployeeMapper;
import belfastmet.P2A1.model.Employee;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author macbookuser
 */
public class EmployeeLauncher extends JFrame {
    
    private EmployeeActions employeeActions;
    private EmployeeForm employeeForm;
    private EmployeeFrame employeeFrame;
    private Employee user;
    
    private EmployeeCrud employeeCrud;
    private MainFrame mainFrame;
    
    public EmployeeLauncher(Employee user){
        this.user = user;
    
    }
       
    //Sets up the java form that displays the panels
    public void setup(){
                this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                GridLayout gridLayout = new GridLayout(1,3);
                this.setLayout(gridLayout);
                
                this.setupEmployeeCrud();

                this.setupEmployeeFrame();
                this.setupEmployeeActions();
                this.setupEmployeeForm();
                
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
    
    private void setupEmployeeCrud(){
        this.employeeCrud = new EmployeeCrud(new EmployeeMapper());
    }
    
    
    //Sets up the buttons for the actions. Checks access level
    private void setupEmployeeActions(){
        if("Manager".equals(user.getAccessLevel())){
            employeeActions = new EmployeeActions(user);
            employeeActions.setOpaque(true);
            this.add(employeeActions);
            employeeActions.setEmployeeLauncher(this);
        }
    }
    
    //Sets up the form also checks access level
    private void setupEmployeeForm(){
        if("Manager".equals(user.getAccessLevel())){
            employeeForm = new EmployeeForm(employeeCrud, user);
            employeeForm.setOpaque(true);
            this.add(employeeForm);
            employeeForm.setEmployeeLauncher(this);
        }
    }
    
    private void setupEmployeeFrame(){
        employeeFrame = new EmployeeFrame(employeeCrud, user);
        employeeFrame.setOpaque(true);
        this.add(employeeFrame);
        employeeFrame.setEmployeeLauncher(this);
    }
    
    //Sets up the create function
    public void create(){
        employeeForm.createEmployee();
    }
        
    //Sets up the update function
    public void update(){
        employeeForm.updateEmployee(employeeFrame.getSelected());
    }
        
    //Sets up the delete function
    public void delete(){
        employeeCrud.delete(employeeCrud.read(employeeFrame.getSelected()));
        refresh();
    };
    
    //Refreshes the tables after a function is carried out
    public void refresh(){
        employeeFrame.refresh();
    }
    
    public void setMainFrame(MainFrame mainFrame){
        this.mainFrame = mainFrame;
    }
    
    public void close(){
        this.setVisible(false);
        mainFrame.setVisible(true);
    }
        
        
        
}
    
    
   
