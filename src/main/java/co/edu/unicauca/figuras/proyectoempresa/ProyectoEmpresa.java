/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.figuras.proyectoempresa;

import co.edu.unicauca.proyectocurso.access.DatabaseConnection;
import co.edu.unicauca.proyectocurso.access.ProjectRepositoryImpl;
import co.edu.unicauca.proyectocurso.access.StudentRepositoryImpl;
import co.edu.unicauca.proyectocurso.domain.entities.Company;
import co.edu.unicauca.proyectocurso.domain.entities.Project;
import co.edu.unicauca.proyectocurso.domain.entities.ProjectState;
import co.edu.unicauca.proyectocurso.domain.entities.Student;
import co.edu.unicauca.proyectocurso.presentation.GUICoordProyPendientes;
import co.edu.unicauca.proyectocurso.presentation.GUIRegistrarUsuarios;
import co.edu.unicauca.proyectocurso.presentation.GUILogin;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.UUID;
import javax.swing.SwingUtilities;

/**
 *
 * @author yeixongec
 */
public class ProyectoEmpresa {

    public static void main(String[] args) {
        
         ProjectRepositoryImpl repository = new ProjectRepositoryImpl();
         StudentRepositoryImpl srepository = new StudentRepositoryImpl();
////
////            Student student2 = srepository.findById(UUID.fromString("3d26fb95-71d8-4be5-bc7e-6f0b253a741d"));
//            Project project = repository.findById(UUID.fromString("22f9264d-4085-4bac-b3db-2d43a48070c5"));
////            
////            project.addStudent(student2);
//    if (project != null) {
//        List<Student> students = project.getStudents();
////
//        if (!students.isEmpty()) {
//            System.out.println("📌 Lista de estudiantes del proyecto '" + project.getName() + "':");
//            for (Student student : students) {
//                System.out.println("   - 🎓 " + student.getFirstName() + " (ID: " + student.getId() + ")");
//            }
//        } else {
//            System.out.println("⚠️ El proyecto '" + project.getName() + "' no tiene estudiantes asociados.");
//        }
//    } else {
//        System.out.println("❌ No se encontró un proyecto con el ID " );
//    }


        // 🔹 1. Probar el método save()
//        Company empresa = new Company();
//        empresa.setNit("123456789"); // NIT existente en la base de datos
//        empresa.setName("Tech Corp");
//        
//
//        Project newProject = new Project();
//        newProject.setId(UUID.randomUUID());
//        newProject.setName("SUICIDIO");
//        newProject.setDescription("NIKA");
//        newProject.setDate(java.time.LocalDate.now());
//        newProject.setState(ProjectState.RECEIVED);
//        newProject.setBudget(10000000);
//        newProject.setMaxMonths(12);
//        newProject.setObjectives("cagadon");
//        newProject.setCompany(empresa);
//
//       boolean isSaved = repository.save(newProject, empresa.getNit());
//       System.out.println("✅ Proyecto guardado: " + isSaved);


        // 🔹 2. Probar el método findAll()
        // List<Project> proyectos = repository.findAll();
        // System.out.println("🔹 Lista de proyectos:");
        //for (Project p : proyectos) {
        //    System.out.println("📌 " + p.getName() + " - " + p.getDescription() + " ID: " + p.getId() + " (Empresa: " + p.getCompany().getName() + ")");
        //}
            System.out.println("Aplicación iniciada correctamente.");
            java.awt.EventQueue.invokeLater(() -> new GUILogin().setVisible(true));
//        java.awt.EventQueue.invokeLater(() -> new GUICoordProyPendientes().setVisible(true));
    }
}
