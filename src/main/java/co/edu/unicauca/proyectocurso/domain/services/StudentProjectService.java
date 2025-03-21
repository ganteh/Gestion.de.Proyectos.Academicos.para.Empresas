/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.proyectocurso.domain.services;

import co.edu.unicauca.proyectocurso.access.IProjectRepository;
import co.edu.unicauca.proyectocurso.access.IStudentProjectRepository;
import co.edu.unicauca.proyectocurso.access.IStudentRepository;
import co.edu.unicauca.proyectocurso.access.ProjectRepositoryImpl;
import co.edu.unicauca.proyectocurso.access.StudentRepositoryImpl;
import co.edu.unicauca.proyectocurso.domain.entities.Project;
import co.edu.unicauca.proyectocurso.domain.entities.ProjectState;
import co.edu.unicauca.proyectocurso.domain.entities.Student;
import co.edu.unicauca.proyectocurso.domain.entities.StudentProject;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Lenovo pc
 */
public class StudentProjectService extends Observado {
    private final IStudentProjectRepository repository;
    private IProjectRepository projectRepository = new ProjectRepositoryImpl();
    private IStudentRepository studentRepository = new StudentRepositoryImpl();

    public StudentProjectService(IStudentProjectRepository repository) {
        this.repository = repository;
    }
    

    /**
     * Asigna un proyecto a un estudiante.
     * @param studentProject Relación estudiante-proyecto.
     * @param studentID ID del estudiante.
     * @param projectID ID del proyecto.
     * @return true si se asignó correctamente, false en caso contrario.
     */
    public boolean assignProjectToStudent(StudentProject studentProject, String studentID, String projectID) {
        if (studentID == null || studentID.isEmpty() || projectID == null || projectID.isEmpty()) {
            return false;
        }
        return repository.save(studentProject, studentID, projectID);
    }

    /**
     * Obtiene todas las asignaciones de proyectos a estudiantes.
     * @return Lista de asignaciones.
     */
    public List<StudentProject> getAllStudentProjects() {
        return repository.findAll();
    }

    /**
     * Actualiza la información de una asignación de proyecto.
     * @param studentProject Relación actualizada.
     * @return true si se actualizó correctamente, false en caso contrario.
     */
    public boolean updateStudentProject(StudentProject studentProject) {
        if (studentProject == null || studentProject.getId() == -1) {
            return false;
        }
        return repository.update(studentProject);
    }

    /**
     * Elimina una asignación de estudiante a proyecto.
     * @param studentProjectID ID de la relación a eliminar.
     * @return true si se eliminó correctamente, false en caso contrario.
     */
    public boolean removeStudentProject(UUID studentProjectID) {
        if (studentProjectID == null) {
            return false;
        }
        return repository.delete(studentProjectID);
    }
    
    /**
     * Obtiene todas las asignaciones de proyectos que están en estado "RECEIVED".
     * @return Lista de asignaciones en estado RECEIVED.
     */
    public List<StudentProject> getReceivedStudentProjects() {
        return repository.findAll()
                         .stream()
                         .filter(sp -> sp.getStatus() == StudentProject.Status.RECEIVED)
                         .toList();
    }
    
        public List<StudentProject> getAcceptedStudentProjects() {
        return repository.findAll()
                         .stream()
                         .filter(sp -> sp.getStatus() == StudentProject.Status.ACCEPTED)
                         .toList();
    }
    public List<StudentProject> getAcceptedRejectedProjects() {
        return repository.findAll()
                         .stream()
                         .filter(sp -> sp.getStatus() == StudentProject.Status.REJECTED)
                         .toList();
    }
    
    public void approveStudentProject(StudentProject studentProject) {
        studentProject.setStatus(StudentProject.Status.ACCEPTED);
        
        repository.update(studentProject);
        notifyObservers();  // Notifica a los observadores (la GUI)
    }
    

    public void rejectStudentProject(StudentProject studentProject) {
        studentProject.setStatus(StudentProject.Status.REJECTED);
        repository.update(studentProject);
        notifyObservers();
    }
    
   
    

}
