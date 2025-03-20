/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.proyectocurso.domain.services;

import co.edu.unicauca.proyectocurso.access.StudentRepositoryImpl;
import co.edu.unicauca.proyectocurso.domain.entities.Student;
import java.util.List;

public class StudentService extends Observado {
    private StudentRepositoryImpl StudentRepository;
    
    public StudentService(StudentRepositoryImpl repository) {
        this.StudentRepository = repository;
    }
    
    public StudentService() {
        this.StudentRepository = new StudentRepositoryImpl();
    }
    
    /**
     * Registra un nuevo estudiante en el sistema
     */
    public boolean registerStudent(String username, String password, String firstName, String lastName, String program, String project_id) {
        if (StudentRepository.estudanteExists(username)) {
            System.out.println("❌ El Estudiante ya existe.");
            return false;
        }
        return StudentRepository.registerStudent(username, password, firstName, lastName, program, project_id);
    }
    
    /**
     * Obtiene la lista de todos los estudiantes
     */
    public List<Student> listEstudiantes() {
        return StudentRepository.findAll();
    }
    
    /**
     * Obtiene la lista de proyectos disponibles
     */
    public List<Object[]> getAvailableProjects() {
        return StudentRepository.findAvailableProjects();
    }
    
    /**
     * Asigna un proyecto a un estudiante usando el nombre de usuario
     */
    public boolean assignProjectToStudent(String username, String projectId) {
        Student student = getStudentByUsername(username);
        if (student == null) {
        return false;
    }

    // Verificar si el estudiante ya está postulado en este proyecto
    if (StudentRepository.isStudentAlreadyApplied(student.getId().toString(), projectId)) {
        return false; // Evita registrar dos veces el mismo estudiante en un proyecto
    }

    return StudentRepository.insertStudentProject(student.getId().toString(), projectId, "RECEIVED");
}

    
    /**
     * Obtiene un estudiante por su nombre de usuario
     */
    public Student getStudentByUsername(String username) {
        return StudentRepository.findByUsername(username);
    }
    
}