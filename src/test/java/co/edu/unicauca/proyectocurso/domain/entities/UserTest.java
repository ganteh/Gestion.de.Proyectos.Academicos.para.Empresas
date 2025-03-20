/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package co.edu.unicauca.proyectocurso.domain.entities;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author adcam
 */
public class UserTest {
    
    private User adminUser;
    private User estudianteUser;
    private User profesorUser;
    private User emptyUser;
    
    @Before
    public void setUp() {
        // Inicializar usuarios con diferentes datos para las pruebas
        adminUser = new User("admin123", "adminPass", "Admin");
        estudianteUser = new User("estudiante1", "estPass123", "Estudiante");
        profesorUser = new User("profesor1", "profSecure456", "Coordinador");

    }
    
    @Test
    public void testGetUsername_Admin() {
        System.out.println("getUsername - Admin");
        String expResult = "admin123";
        String result = adminUser.getUsername();
        assertEquals("El nombre de usuario debería ser 'admin123'", expResult, result);
    }
    
    @Test
    public void testGetUsername_Estudiante() {
        System.out.println("getUsername - Estudiante");
        String expResult = "estudiante1";
        String result = estudianteUser.getUsername();
        assertEquals("El nombre de usuario debería ser 'estudiante1'", expResult, result);
    }
    

    @Test
    public void testSetUsername_NormalValue() {
        System.out.println("setUsername - Valor normal");
        String username = "nuevoUsuario";
        adminUser.setUsername(username);
        assertEquals("El nombre de usuario debería cambiar a 'nuevoUsuario'", username, adminUser.getUsername());
    }
    
    @Test
    public void testSetUsername_EmptyValue() {
        System.out.println("setUsername - Valor vacío");
        String username = "";
        estudianteUser.setUsername(username);
        assertEquals("El nombre de usuario debería cambiar a cadena vacía", username, estudianteUser.getUsername());
    }
    
    @Test
    public void testSetUsername_NullValue() {
        System.out.println("setUsername - Valor nulo");
        String username = null;
        profesorUser.setUsername(username);
        assertNull("El nombre de usuario debería cambiar a null", profesorUser.getUsername());
    }
    
    @Test
    public void testGetPassword_Admin() {
        System.out.println("getPassword - Admin");
        String expResult = "adminPass";
        String result = adminUser.getPassword();
        assertEquals("La contraseña debería ser 'adminPass'", expResult, result);
    }
    
    @Test
    public void testGetPassword_Estudiante() {
        System.out.println("getPassword - Estudiante");
        String expResult = "estPass123";
        String result = estudianteUser.getPassword();
        assertEquals("La contraseña debería ser 'estPass123'", expResult, result);
    }
    
    @Test
    public void testSetPassword_NormalValue() {
        System.out.println("setPassword - Valor normal");
        String password = "nuevaContraseña";
        adminUser.setPassword(password);
        assertEquals("La contraseña debería cambiar a 'nuevaContraseña'", password, adminUser.getPassword());
    }
    
    @Test
    public void testSetPassword_EmptyValue() {
        System.out.println("setPassword - Valor vacío");
        String password = "";
        estudianteUser.setPassword(password);
        assertEquals("La contraseña debería cambiar a cadena vacía", password, estudianteUser.getPassword());
    }
    
    @Test
    public void testSetPassword_NullValue() {
        System.out.println("setPassword - Valor nulo");
        String password = null;
        profesorUser.setPassword(password);
        assertNull("La contraseña debería cambiar a null", profesorUser.getPassword());
    }
    
    @Test
    public void testGetRole_Admin() {
        System.out.println("getRole - Admin");
        String expResult = "Admin";
        String result = adminUser.getRole();
        assertEquals("El rol debería ser 'Admin'", expResult, result);
    }
    
    @Test
    public void testGetRole_Estudiante() {
        System.out.println("getRole - Estudiante");
        String expResult = "Estudiante";
        String result = estudianteUser.getRole();
        assertEquals("El rol debería ser 'Estudiante'", expResult, result);
    }

    
    @Test
    public void testSetRole_NormalValue() {
        System.out.println("setRole - Valor normal");
        String role = "SUPERVISOR";
        adminUser.setRole(role);
        assertEquals("El rol debería cambiar a 'SUPERVISOR'", role, adminUser.getRole());
    }
    
    @Test
    public void testSetRole_EmptyValue() {
        System.out.println("setRole - Valor vacío");
        String role = "";
        estudianteUser.setRole(role);
        assertEquals("El rol debería cambiar a cadena vacía", role, estudianteUser.getRole());
    }
    
    @Test
    public void testSetRole_NullValue() {
        System.out.println("setRole - Valor nulo");
        String role = null;
        profesorUser.setRole(role);
        assertNull("El rol debería cambiar a null", profesorUser.getRole());
    }
    
    @Test
    public void testToString_Admin() {
        System.out.println("toString - Admin");
        String expected = "User{username=admin123, role=Admin}";
        String result = adminUser.toString();
        assertEquals("La representación en cadena no coincide con lo esperado", expected, result);
    }
    
    @Test
    public void testToString_Estudiante() {
        System.out.println("toString - Estudiante");
        String expected = "User{username=estudiante1, role=Estudiante}";
        String result = estudianteUser.toString();
        assertEquals("La representación en cadena no coincide con lo esperado", expected, result);
    }
    
    @Test
    public void testToString_Empty() {
        System.out.println("toString - Usuario vacío");
        String expected = "User{username=null, password=null, role=null, profileCompleted=false}";
    }
    
    @Test
    public void testConstructor_ThreeParametersNoProfileCompleted() {
        System.out.println("Constructor - Tres parámetros sin profileCompleted");
        User user = new User("testUser", "testPass", "TEST");
        assertEquals("testUser", user.getUsername());
        assertEquals("testPass", user.getPassword());
        assertEquals("TEST", user.getRole());
    }
    
    @Test
    public void testConstructor_FourParameters() {
        System.out.println("Constructor - Cuatro parámetros");
        User user = new User("testUser", "testPass", "TEST");
        assertEquals("testUser", user.getUsername());
        assertEquals("testPass", user.getPassword());
        assertEquals("TEST", user.getRole());
    }
}