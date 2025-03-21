/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.unicauca.proyectocurso.access;

import co.edu.unicauca.proyectocurso.domain.entities.Project;
import co.edu.unicauca.proyectocurso.domain.entities.Student;
import co.edu.unicauca.proyectocurso.domain.entities.StudentProject;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 *
 * @author Lenovo pc
 */
public interface IStudentProjectRepository {

    boolean save(StudentProject studentProject, String StudentID, String ProjectID);

    List<StudentProject> findAll();


    boolean update(StudentProject studentProject);
    
     boolean delete(UUID studentProjectID); 
     
     
     

}
