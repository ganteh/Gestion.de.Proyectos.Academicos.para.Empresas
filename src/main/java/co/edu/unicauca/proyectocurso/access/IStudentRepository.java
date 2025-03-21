/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.proyectocurso.access;
import co.edu.unicauca.proyectocurso.domain.entities.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
/**
 *
 * @author yeixongec
 */
public interface IStudentRepository {
    /**
     * Guarda un proyecto en la base de datos asociado a una empresa.
     * @param student Estudiante a registrar.
     * @return true si se guardó correctamente, false en caso contrario.
     */
    boolean save(Student student);

    /**
     * Obtiene la lista de todos los estudiantes registrados.
     * @return Lista de proyectos.
     */
    List<Student> findAll();

    /**
     *
     * @param student estudiante a actualizar
     * @return True si el proyecto se actualizo correctamente, en caso contrario False
     */
    public boolean update(Student student);
    
    Student findById(UUID studentId);
    
    ArrayList<Student> findStudentsByProjectId(String projectId);
}
