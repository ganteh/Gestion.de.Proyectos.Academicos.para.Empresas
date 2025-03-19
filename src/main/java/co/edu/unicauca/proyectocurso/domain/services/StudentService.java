/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.proyectocurso.domain.services;

import co.edu.unicauca.proyectocurso.access.IStudentRepository;
import co.edu.unicauca.proyectocurso.access.StudentRepositoryImpl;
import co.edu.unicauca.proyectocurso.domain.entities.Student;
import java.util.List;
/**
 *
 * @author yeixongec
 */
public class StudentService extends Observado{
   private StudentRepositoryImpl StudentRepository = new StudentRepositoryImpl(); ;

    public StudentService(StudentRepositoryImpl repository) {
        this.StudentRepository = repository;
    }
    
    public StudentService(){
        
    }

    public boolean registerStudent(String username, String password, String firstName, String lastName, String program, String project_id){
        if (StudentRepository.estudanteExists(username)) {
            System.out.println("❌ El Estudiante ya existe.");
            return false;
        }
        return StudentRepository.registerStudent(username, password, firstName, lastName, program, project_id);
    }

    public List<Student> listProjects() {
        return StudentRepository.findAll();
    }
    
}
