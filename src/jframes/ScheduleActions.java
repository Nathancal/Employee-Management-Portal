/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jframes;

import belfastmet.P2A1.model.Employee;

/**
 *
 * @author macbookuser
 */
public class ScheduleActions extends javax.swing.JPanel {

    private ScheduleLauncher scheduleLauncher;
    private Employee user;
    /**
     * Creates new form ScheduleActions
     */
    public ScheduleActions(Employee user) {
        initComponents();
        this.user = user;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCreateShift = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();

        btnCreateShift.setText("Create Shift");
        btnCreateShift.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateShiftActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete Shift");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCreateShift, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCreateShift)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDelete)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCreateShiftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateShiftActionPerformed
        //Create button
        scheduleLauncher.create();
    }//GEN-LAST:event_btnCreateShiftActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        //Delete button
        scheduleLauncher.delete();
        scheduleLauncher.refresh();

    }//GEN-LAST:event_btnDeleteActionPerformed

    public ScheduleLauncher getScheduleFrame(){
        return scheduleLauncher;
    
    }
    
    public void setScheduleLauncher(ScheduleLauncher scheduleLauncher){
        this.scheduleLauncher = scheduleLauncher;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreateShift;
    private javax.swing.JButton btnDelete;
    // End of variables declaration//GEN-END:variables
}