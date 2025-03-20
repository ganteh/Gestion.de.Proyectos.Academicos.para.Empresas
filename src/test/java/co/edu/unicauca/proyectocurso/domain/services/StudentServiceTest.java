/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package co.edu.unicauca.proyectocurso.domain.services;




import co.edu.unicauca.proyectocurso.access.StudentRepositoryImpl;
import co.edu.unicauca.proyectocurso.domain.entities.Student;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StudentServiceTest {

    private StudentService studentService;
    private StudentRepositoryImpl studentRepositoryMock;

    @BeforeEach
    public void setUp() {
        // Crear un mock del repositorio
        studentRepositoryMock = Mockito.mock(StudentRepositoryImpl.class);
        studentService = new StudentService(studentRepositoryMock);
    }

    @Test
    public void testRegisterStudent_Success() {
        System.out.println("Prueba: Registrar estudiante correctamente");

        // Simular que el estudiante NO existe en la BD
        when(studentRepositoryMock.estudanteExists("testuser")).thenReturn(false);
        when(studentRepositoryMock.registerStudent(anyString(), anyString(), anyString(), anyString(), anyString(), anyString()))
            .thenReturn(true);

        boolean result = studentService.registerStudent("testuser", "1234", "Juan", "Perez", "Ingeniería", "123-proj");

        assertTrue(result, "El estudiante debería haberse registrado exitosamente.");
    }

    @Test
    public void testRegisterStudent_Fail_AlreadyExists() {
        System.out.println("Prueba: No permitir registrar un estudiante duplicado");

        // Simular que el estudiante YA existe en la BD
        when(studentRepositoryMock.estudanteExists("testuser")).thenReturn(true);

        boolean result = studentService.registerStudent("testuser", "1234", "Juan", "Perez", "Ingeniería", "123-proj");

        assertFalse(result, "No debería permitir registrar un estudiante duplicado.");
    }

    @Test
    public void testAssignProjectToStudent_Success() {
        System.out.println("Prueba: Asignar un proyecto a un estudiante correctamente");

        UUID studentId = UUID.randomUUID();
        Student mockStudent = new Student("testuser", "1234", "Juan", "Perez", "Ingeniería", null);
        mockStudent.setId(studentId);

        // Simular que el estudiante existe
        when(studentRepositoryMock.findByUsername("testuser")).thenReturn(mockStudent);
        // Simular que el estudiante NO está ya en el proyecto
        when(studentRepositoryMock.isStudentAlreadyApplied(anyString(), anyString())).thenReturn(false);
        // Simular inserción exitosa en student_projects
        when(studentRepositoryMock.insertStudentProject(anyString(), anyString(), anyString())).thenReturn(true);

        boolean result = studentService.assignProjectToStudent("testuser", "123-proj");

        assertTrue(result, "El estudiante debería haber sido asignado al proyecto.");
    }

    @Test
    public void testAssignProjectToStudent_Fail_AlreadyApplied() {
        System.out.println("Prueba: No permitir que un estudiante se postule dos veces al mismo proyecto");

        UUID studentId = UUID.randomUUID();
        Student mockStudent = new Student("testuser", "1234", "Juan", "Perez", "Ingeniería", null);
        mockStudent.setId(studentId);

        // Simular que el estudiante existe
        when(studentRepositoryMock.findByUsername("testuser")).thenReturn(mockStudent);
        // Simular que el estudiante YA ESTÁ postulado en el proyecto
        when(studentRepositoryMock.isStudentAlreadyApplied(anyString(), anyString())).thenReturn(true);

        boolean result = studentService.assignProjectToStudent("testuser", "123-proj");

        assertFalse(result, "No debería permitir postularse dos veces al mismo proyecto.");
    }

    @Test
    public void testGetStudentByUsername() {
        System.out.println("Prueba: Obtener estudiante por username");

        Student expectedStudent = new Student("testuser", "1234", "Juan", "Perez", "Ingeniería", null);
        when(studentRepositoryMock.findByUsername("testuser")).thenReturn(expectedStudent);

        Student result = studentService.getStudentByUsername("testuser");

        assertNotNull(result, "El estudiante no debería ser nulo.");
        assertEquals("Juan", result.getFirstName(), "El nombre del estudiante debería coincidir.");
    }
}
