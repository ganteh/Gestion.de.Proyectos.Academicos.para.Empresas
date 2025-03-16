/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package co.edu.unicauca.proyectocurso.presentation;

import co.edu.unicauca.proyectocurso.access.DatabaseConnection;
import co.edu.unicauca.proyectocurso.access.IProjectRepository;
import co.edu.unicauca.proyectocurso.access.ProjectRepositoryImpl;
import co.edu.unicauca.proyectocurso.domain.entities.*;
import co.edu.unicauca.proyectocurso.domain.services.Observer;
import co.edu.unicauca.proyectocurso.domain.services.ProjectService;
import java.awt.BorderLayout;
import java.sql.Connection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lenovo pc
 */
public class GUICoordProyPendientes extends javax.swing.JFrame implements Observer {
    private ProjectService projectService = new ProjectService();
    DefaultTableModel model = new DefaultTableModel();
    

    /**
     * Creates new form GUICoordProyPendientes
     */
    public GUICoordProyPendientes() {
        initComponents();
        this.projectService = projectService;
        
        // Suscribirse como observador
        this.projectService.addObserver(this);

        String[] columnas = { "Nombre", "Descripción", "Empresa" };
        model.setColumnIdentifiers(columnas);
        jTable1.setModel(model);
        
        cargarProyectos();
        
    }
    
    public GUICoordProyPendientes(ProjectService projectService) { 
        initComponents();
        this.projectService = projectService;
        

        // Suscribirse como observador
        this.projectService.addObserver(this);

        String[] columnas = { "Nombre", "Descripción", "Empresa" };
        model.setColumnIdentifiers(columnas);
        jTable1.setModel(model);
        
        cargarProyectos();
    }
    
    private void cargarProyectos() {
        List<Project> proyectos = projectService.getPendingProjects();
        model.setRowCount(0);
        for (Project p : proyectos) {
            model.addRow(new Object[]{p.getName(), p.getDescription(), p.getCompany().getName()});
        }
    }
    
    @Override
    public void update() {
        cargarProyectos();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Proyectos Pendientes");

        jLabel1.setText("PROYECTOS PENDIENTES");

        jTable1.setModel(model);
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setText("Justificación");

        jTextField1.setToolTipText("");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton1.setText("APROBAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("RECHAZAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Ver detalles del proyecto");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Volver");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton4))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(61, 61, 61)
                                                .addComponent(jButton3))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(32, 32, 32)
                                                .addComponent(jButton1)
                                                .addGap(54, 54, 54)
                                                .addComponent(jButton2)))
                                        .addGap(0, 0, Short.MAX_VALUE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(107, 107, 107)
                                .addComponent(jLabel2))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(206, 206, 206)
                                .addComponent(jLabel1)))
                        .addGap(0, 201, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int filaSeleccionada = jTable1.getSelectedRow();
        if (filaSeleccionada >= 0) {
            String nombreProyecto = (String) model.getValueAt(filaSeleccionada, 0);
            Project proyecto = projectService.getPendingProjects()
                    .stream().filter(p -> p.getName().equals(nombreProyecto))
                    .findFirst().orElse(null);

            if (proyecto != null) {
                projectService.approveProject(proyecto);
                JOptionPane.showMessageDialog(this, "Proyecto aprobado");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un proyecto");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
         int filaSeleccionada = jTable1.getSelectedRow();
        String justificacion = jTextField1.getText().trim();

        if (filaSeleccionada >= 0) {
            if (justificacion.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe escribir una justificación.");
                return;
            }

            String nombreProyecto = (String) model.getValueAt(filaSeleccionada, 0);
            Project proyecto = projectService.getPendingProjects()
                    .stream().filter(p -> p.getName().equals(nombreProyecto))
                    .findFirst().orElse(null);

            if (proyecto != null) {
                projectService.rejectProject(proyecto, justificacion);
                JOptionPane.showMessageDialog(this, "Proyecto rechazado con justificación: " + justificacion);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un proyecto");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int filaSeleccionada = jTable1.getSelectedRow();

        if (filaSeleccionada >= 0) {
            String nombreProyecto = (String) jTable1.getValueAt(filaSeleccionada, 0); // Obtener nombre desde la tabla

            // Buscar el proyecto en la lista de proyectos pendientes
            Project proyecto = projectService.getPendingProjects()
                    .stream()
                    .filter(p -> p.getName().equals(nombreProyecto))
                    .findFirst()
                    .orElse(null);

            if (proyecto != null) {
                GUIDetallesProyecto detalles = new GUIDetallesProyecto(proyecto); // Abrir la ventana con detalles
                detalles.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró el proyecto seleccionado.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un proyecto de la tabla.");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUICoordProyPendientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUICoordProyPendientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUICoordProyPendientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUICoordProyPendientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            
            public void run() {
               new GUICoordProyPendientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables



   
}
