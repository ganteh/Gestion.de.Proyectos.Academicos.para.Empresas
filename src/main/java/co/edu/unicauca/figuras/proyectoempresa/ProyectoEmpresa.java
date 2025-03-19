/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.figuras.proyectoempresa;

import co.edu.unicauca.proyectocurso.access.DatabaseConnection;
import co.edu.unicauca.proyectocurso.access.ProjectRepositoryImpl;
import co.edu.unicauca.proyectocurso.domain.entities.Company;
import co.edu.unicauca.proyectocurso.domain.entities.Project;
import co.edu.unicauca.proyectocurso.domain.entities.ProjectState;
import co.edu.unicauca.proyectocurso.presentation.GUICoordProyPendientes;
import co.edu.unicauca.proyectocurso.presentation.GUIRegistrarUsuarios;
import co.edu.unicauca.proyectocurso.presentation.GUILogin;
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

        // 🔹 1. Probar el método save()
//        Company empresa = new Company();
//        empresa.setNit("123456789"); // NIT existente en la base de datos
//        empresa.setName("Tech Corp");
//        
//
//        Project newProject = new Project();
//        newProject.setId(UUID.randomUUID());
//        newProject.setName("Nuevo Proyecto");
//        newProject.setDescription("Este es un proyecto de prueba");
//        newProject.setDate(java.time.LocalDate.now());
//        newProject.setState(ProjectState.IN_EXECUTION);
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
    
