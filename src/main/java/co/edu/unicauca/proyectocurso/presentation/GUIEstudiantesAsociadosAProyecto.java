/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package co.edu.unicauca.proyectocurso.presentation;

import co.edu.unicauca.proyectocurso.access.IProjectRepository;
import co.edu.unicauca.proyectocurso.access.IStudentRepository;
import co.edu.unicauca.proyectocurso.access.ProjectRepositoryImpl;
import co.edu.unicauca.proyectocurso.access.StudentRepositoryImpl;
import co.edu.unicauca.proyectocurso.domain.entities.Project;
import co.edu.unicauca.proyectocurso.domain.entities.Student;
import co.edu.unicauca.proyectocurso.domain.entities.StudentProject;
import co.edu.unicauca.proyectocurso.domain.services.Observer;
import co.edu.unicauca.proyectocurso.domain.services.ProjectService;
import java.util.List;
import java.util.UUID;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lenovo pc
 */
public class GUIEstudiantesAsociadosAProyecto extends javax.swing.JFrame {
    private IStudentRepository studentRepository = new StudentRepositoryImpl();
    private IProjectRepository repository = new ProjectRepositoryImpl(); 
    private ProjectService projectService = new ProjectService();
    DefaultTableModel model = new DefaultTableModel();
    /**
     * Creates new form GUIEstudiantesAsociadosAProyecto
     */
    public GUIEstudiantesAsociadosAProyecto() {
        initComponents();
    }
    
    public GUIEstudiantesAsociadosAProyecto(Project proyecto) {
        initComponents();
        String[] columnas = { "Nombre", "Apellido", "Programa", "Id" };
        model.setColumnIdentifiers(columnas);
        jTable1.setModel(model);
        
        cargarEstudiantes(proyecto);
    }
    

    
    private void cargarEstudiantes(Project proyecto){
       List<Student> students = studentRepository.findStudentsByProjectId(proyecto.getId().toString());
       model.setRowCount(0);
       for (Student p : students) {
            model.addRow(new Object[]{p.getFirstName(), p.getLastName(), p.getProgram(), p.getId()});
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jTable1.setModel(model);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(82, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(108, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(GUIEstudiantesAsociadosAProyecto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUIEstudiantesAsociadosAProyecto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUIEstudiantesAsociadosAProyecto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUIEstudiantesAsociadosAProyecto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIEstudiantesAsociadosAProyecto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables




}
