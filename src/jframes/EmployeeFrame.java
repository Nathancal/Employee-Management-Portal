package jframes;

import belfastmet.P2A1.db.EmployeeCrud;
import belfastmet.P2A1.mapper.EmployeeMapper;
import belfastmet.P2A1.model.Employee;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author macbookuser
 */
public class EmployeeFrame extends javax.swing.JPanel { 
    
    //Here the table columns for displaying the employee data are created.
    private static final String[] columnNames = {"EmployeeID","First Name","Last Name","Access Level","Address","DOB","Email","Contact Number"};

    //Variables
    private EmployeeCrud employeeCrud;
    private int selected;
    private EmployeeLauncher employeeLauncher;
    private Employee user;
    
    //Multidimensional array to display the data
    Object[][] data;
    JTable table;
    
    /**
     * Creates new form EmployeeFrame that displays a table
     */
    public EmployeeFrame(EmployeeCrud employeeCrud, Employee user) {
        super(new GridLayout(1,3));
        this.employeeCrud = employeeCrud;
        this.user = user;
        
        refresh();              
    }

    //This method populates the table with employee data
    public Object[][] getData(){
        //Declares the array of Employees
        Employee[] employees;
        
        //Here we determine if a user is a manager or an employee, only 
        //managers may manipulate the records in the table, an employee may only
        //view their own records.
        if("Manager".equals(user.getAccessLevel())){
            employees = employeeCrud.readAll();
        } else {
            employees = employeeCrud.readAllForUser(user.getEmployeeID());
        }
        
        //Here a list of employees is created
        List<Employee> employeeList =  Arrays.asList(employees);
        
        //The multidimensional array displays a number of rows that is the length
        //of the employeeList. This means the table will display the same number of rows
        //as is stored in the DB. The [8] displays the total number of column headings.
        Object[][] returnObjectArray = new Object[employeeList.size()][8];
        
        //Cycles through the array beginning at position 0
        int i = 0;
        
        for(Employee employee: employeeList){
            returnObjectArray[i][0] = employee.getEmployeeID();
            returnObjectArray[i][1] = employee.getFirstName();
            returnObjectArray[i][2] = employee.getLastName();
            returnObjectArray[i][3] = employee.getAccessLevel();
            returnObjectArray[i][4] = employee.getAddress();
            returnObjectArray[i][5] = employee.getDob();
            returnObjectArray[i][6] = employee.getEmail();
            returnObjectArray[i][7] = employee.getContactNum();

            i++;
        }
        return returnObjectArray;
    
    }
    
    public int getSelected(){
        return this.selected;
    }

    public EmployeeLauncher getEmployeeLauncher() {
        return employeeLauncher;
    }

    public void setEmployeeLauncher(EmployeeLauncher employeeLauncher) {
        this.employeeLauncher = employeeLauncher;
    }
    
    //Refreshes the data in the table after an action is performed
    public void refresh(){
        this.selected = 0;
        this.removeAll();
        data = getData();   
        
        //Creates the table that displays the data gathered in the arrays
        table = new JTable(data, columnNames);
        table.setModel(new DefaultTableModel(data, columnNames){
            @Override 
            public boolean isCellEditable(int row, int column){
                return false;//Override method that stops individual cells from being editable
            
            }
        
        });
        table.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e){
                JTable table = (JTable) e.getSource();
                selected = (Integer)table.getValueAt(table.getSelectedRow(), 0);//Tells the user which row has been selected
                table.clearSelection();
                System.out.println(data[selected][1]);
            }
        
        });
        
        table.setPreferredScrollableViewportSize(new Dimension(1000,70));
        table.setFillsViewportHeight(false);
        
//        
//        table.getColumnModel().getColumn(0).setPreferredWidth(27);
//        table.getColumnModel().getColumn(1).setPreferredWidth(120);
//        table.getColumnModel().getColumn(2).setPreferredWidth(100);
//        table.getColumnModel().getColumn(3).setPreferredWidth(90);
//        table.getColumnModel().getColumn(4).setPreferredWidth(90);
//        table.getColumnModel().getColumn(6).setPreferredWidth(120);
//        table.getColumnModel().getColumn(7).setPreferredWidth(100);
//        table.getColumnModel().getColumn(8).setPreferredWidth(95);
//        table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);

        JScrollPane scrollPane = new JScrollPane(table);
        
        add(scrollPane);
      
        
    }
}