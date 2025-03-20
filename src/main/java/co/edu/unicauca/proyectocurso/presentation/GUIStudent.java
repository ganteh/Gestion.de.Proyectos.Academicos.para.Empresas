/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package co.edu.unicauca.proyectocurso.presentation;

import co.edu.unicauca.proyectocurso.domain.entities.Project;
import co.edu.unicauca.proyectocurso.domain.entities.Student;
import co.edu.unicauca.proyectocurso.domain.services.Observer;
import co.edu.unicauca.proyectocurso.domain.services.ProjectService;
import co.edu.unicauca.proyectocurso.domain.services.StudentService;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author adcam
 */
public class GUIStudent extends javax.swing.JFrame implements Observer{

    /**
     * Creates new form GUIUser
     */
    private StudentService studentService;
    private DefaultTableModel tableModel = new DefaultTableModel();
    private ProjectService projectService;
    private String username;
    private Student currentStudent;
    
       public GUIStudent() 
       {
           initComponents(); 
           this.studentService = new StudentService(); 
           this.projectService = new ProjectService();
           this.studentService.addObserver(this);
           String[] columns = {"ID", "Fecha", "Empresa", "Nombre Proyecto", "Descripción"};
           tableModel.setColumnIdentifiers(columns);
           tableProjects.setModel(tableModel);
           cargarProyectos();
       }

    public GUIStudent(String username) {
        initComponents();
        this.username = username;
        this.studentService = new StudentService();
        this.projectService = new ProjectService();
        this.currentStudent = studentService.getStudentByUsername(username);
        
        if (this.currentStudent == null) {
            JOptionPane.showMessageDialog(this, "No se pudo cargar la información del estudiante", "Error", JOptionPane.ERROR_MESSAGE);
            dispose();
            return;
        }
            
           String[] columns = {"ID", "Fecha", "Empresa", "Nombre Proyecto", "Descripción"};
           tableModel.setColumnIdentifiers(columns);
           tableProjects.setModel(tableModel);
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
        tableProjects = new javax.swing.JTable();
        btnPostularse = new javax.swing.JButton();
        btnDetalles = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("ESTUDIANTE");

        jScrollPane1.setViewportView(tableProjects);

        btnPostularse.setText("Postularse");
        btnPostularse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPostularseActionPerformed(evt);
            }
        });

        btnDetalles.setText("Ver Detalles Proyecto");
        btnDetalles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetallesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(344, 344, 344))
            .addGroup(layout.createSequentialGroup()
                .addGap(294, 294, 294)
                .addComponent(btnPostularse)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDetalles)
                .addGap(119, 119, 119))
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 668, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPostularse)
                    .addComponent(btnDetalles))
                .addContainerGap(75, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPostularseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPostularseActionPerformed
        int selectedRow = tableProjects.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un proyecto para postularse", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        String projectId = tableModel.getValueAt(selectedRow, 0).toString();
        String projectName = tableModel.getValueAt(selectedRow, 3).toString();
        
        int confirm = JOptionPane.showConfirmDialog(this, 
                "¿Está seguro que desea postularse al proyecto: " + projectName + "?", 
                "Confirmar Postulación", 
                JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                boolean result = studentService.assignProjectToStudent(username, projectId);
                
                if (result) {
                    JOptionPane.showMessageDialog(this, 
                            "Se ha postulado exitosamente al proyecto", 
                            "Éxito", 
                            JOptionPane.INFORMATION_MESSAGE);
                    cargarProyectos();
                } else {
                    JOptionPane.showMessageDialog(this, 
                            "Usted ya se encuentra postulado en este proyecto.", 
                            "Error", 
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, 
                        "Error al procesar la postulación: " + ex.getMessage(), 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnPostularseActionPerformed

    private void btnDetallesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetallesActionPerformed
        // TODO add your handling code here:
        int filaSeleccionada = tableProjects.getSelectedRow();

        if (filaSeleccionada >= 0) {
            String nombreProyecto = (String) tableProjects.getValueAt(filaSeleccionada, 3); // Obtener nombre desde la tabla

            // Buscar el proyecto en la lista de proyectos pendientes
            Project proyecto = projectService.listProjects()
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
    }//GEN-LAST:event_btnDetallesActionPerformed

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
            java.util.logging.Logger.getLogger(GUIStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIStudent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUIStudent().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDetalles;
    private javax.swing.JButton btnPostularse;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableProjects;
    // End of variables declaration//GEN-END:variables

    private void cargarProyectos() {
        // Limpiar la tabla
        tableModel.setRowCount(0);
        
        // Cargar los proyectos
        List<Object[]> projects = studentService.getAvailableProjects();
        
        for (Object[] project : projects) {
            tableModel.addRow(new Object[]{
                project[0], // ID
                project[1], // Fecha
                project[2], // Empresa
                project[3], // Nombre Proyecto
                project[4]  // Descripción
            });
        }
    }

    @Override
    public void update() {
        cargarProyectos();
    }
}
