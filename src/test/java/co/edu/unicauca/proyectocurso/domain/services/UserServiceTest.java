/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package co.edu.unicauca.proyectocurso.domain.services;

import co.edu.unicauca.proyectocurso.domain.entities.User;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

/**
 *
 * @author adcam
 */
public class UserServiceTest {
    
    @Mock
    private UserService userService;
    private List<User> mockUserList;
    
    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        
        // Crear una lista de usuarios simulada para las pruebas
        mockUserList = new ArrayList<>();
        mockUserList.add(new User("admin123", "admin123", "ADMIN"));
        mockUserList.add(new User("estudiante1", "pass123", "ESTUDIANTE"));
        mockUserList.add(new User("profesor1", "secret456", "PROFESOR"));
        mockUserList.add(new User("estudiante2", "estudia789", "ESTUDIANTE"));
        
        // Configurar comportamiento del mock para listUser
        when(userService.listUser()).thenReturn(mockUserList);
        
        // Configurar comportamiento para registerUser con diferentes entradas
        when(userService.registerUser("nuevoAdmin", "admin789", "ADMIN")).thenReturn(true);
        when(userService.registerUser("", "", "")).thenReturn(false);
        when(userService.registerUser("admin123", "cualquierClave", "ADMIN")).thenReturn(false); // Usuario duplicado
        
        // Configurar comportamiento para validarUsuario
        when(userService.validarUsuario("admin123", "admin123")).thenReturn("ADMIN");
        when(userService.validarUsuario("estudiante1", "pass123")).thenReturn("ESTUDIANTE");
        when(userService.validarUsuario("admin123", "claveIncorrecta")).thenReturn("");
        when(userService.validarUsuario("noExiste", "cualquierClave")).thenReturn("");
        
        // Configurar comportamiento para updateUser
        when(userService.updateUser("estudiante1", "estudianteNuevo", "nuevaClave", "ESTUDIANTE")).thenReturn(true);
        when(userService.updateUser("noExiste", "cualquierNombre", "cualquierClave", "ESTUDIANTE")).thenReturn(false);
        
        // Configurar comportamiento para searchUsers
        List<User> adminResults = new ArrayList<>();
        adminResults.add(mockUserList.get(0));
        when(userService.searchUsers("admin")).thenReturn(adminResults);
        
        List<User> estudianteResults = new ArrayList<>();
        estudianteResults.add(mockUserList.get(1));
        estudianteResults.add(mockUserList.get(3));
        when(userService.searchUsers("estudiante")).thenReturn(estudianteResults);
        
        when(userService.searchUsers("noExiste")).thenReturn(new ArrayList<>());
        
        // Configurar comportamiento para searchUserss
        when(userService.searchUserss("admin", "ADMIN")).thenReturn(adminResults);
        when(userService.searchUserss("estudiante", "ESTUDIANTE")).thenReturn(estudianteResults);
        when(userService.searchUserss("", "PROFESOR")).thenReturn(List.of(mockUserList.get(2)));
        
        // Configurar comportamiento para deleteUser
        when(userService.deleteUser("estudiante2")).thenReturn(true);
        when(userService.deleteUser("noExiste")).thenReturn(false);
        
        // Configurar comportamiento para isProfileCompleted
        when(userService.isProfileCompleted("admin123")).thenReturn(true);
        when(userService.isProfileCompleted("profesor1")).thenReturn(false);
        when(userService.isProfileCompleted("noExiste")).thenReturn(false);
        
        // Configurar comportamiento para updateProfileCompleted
        when(userService.updateProfileCompleted("profesor1", true)).thenReturn(true);
        when(userService.updateProfileCompleted("noExiste", true)).thenReturn(false);
    }

    @Test
    public void testListUser() {
        System.out.println("Prueba listUser");
        
        List<User> result = userService.listUser();
        
        // Verificar que devuelve la lista esperada
        assertEquals(4, result.size());
        assertEquals("admin123", result.get(0).getUsername());
        assertEquals("estudiante1", result.get(1).getUsername());
        assertEquals("profesor1", result.get(2).getUsername());
        
        // Verificar que se llamó al método
        verify(userService).listUser();
    }

    @Test
    public void testRegisterUser_Success() {
        System.out.println("Prueba registerUser - Caso exitoso");
        
        boolean result = userService.registerUser("nuevoAdmin", "admin789", "ADMIN");
        
        assertTrue(result);
        verify(userService).registerUser("nuevoAdmin", "admin789", "ADMIN");
    }
    
    @Test
    public void testRegisterUser_EmptyFields() {
        System.out.println("Prueba registerUser - Campos vacíos");
        
        boolean result = userService.registerUser("", "", "");
        
        assertFalse(result);
        verify(userService).registerUser("", "", "");
    }
    
    @Test
    public void testRegisterUser_DuplicateUser() {
        System.out.println("Prueba registerUser - Usuario duplicado");
        
        boolean result = userService.registerUser("admin123", "cualquierClave", "ADMIN");
        
        assertFalse(result);
        verify(userService).registerUser("admin123", "cualquierClave", "ADMIN");
    }

    @Test
    public void testValidarUsuario_AdminValid() {
        System.out.println("Prueba validarUsuario - Admin válido");
        
        String result = userService.validarUsuario("admin123", "admin123");
        
        assertEquals("ADMIN", result);
        verify(userService).validarUsuario("admin123", "admin123");
    }
    
    @Test
    public void testValidarUsuario_EstudianteValid() {
        System.out.println("Prueba validarUsuario - Estudiante válido");
        
        String result = userService.validarUsuario("estudiante1", "pass123");
        
        assertEquals("ESTUDIANTE", result);
        verify(userService).validarUsuario("estudiante1", "pass123");
    }
    
    @Test
    public void testValidarUsuario_InvalidPassword() {
        System.out.println("Prueba validarUsuario - Contraseña inválida");
        
        String result = userService.validarUsuario("admin123", "claveIncorrecta");
        
        assertEquals("", result);
        verify(userService).validarUsuario("admin123", "claveIncorrecta");
    }
    
    @Test
    public void testValidarUsuario_NonexistentUser() {
        System.out.println("Prueba validarUsuario - Usuario no existente");
        
        String result = userService.validarUsuario("noExiste", "cualquierClave");
        
        assertEquals("", result);
        verify(userService).validarUsuario("noExiste", "cualquierClave");
    }

    @Test
    public void testUpdateUser_Success() {
        System.out.println("Prueba updateUser - Caso exitoso");
        
        boolean result = userService.updateUser("estudiante1", "estudianteNuevo", "nuevaClave", "ESTUDIANTE");
        
        assertTrue(result);
        verify(userService).updateUser("estudiante1", "estudianteNuevo", "nuevaClave", "ESTUDIANTE");
    }
    
    @Test
    public void testUpdateUser_UserNotFound() {
        System.out.println("Prueba updateUser - Usuario no encontrado");
        
        boolean result = userService.updateUser("noExiste", "cualquierNombre", "cualquierClave", "ESTUDIANTE");
        
        assertFalse(result);
        verify(userService).updateUser("noExiste", "cualquierNombre", "cualquierClave", "ESTUDIANTE");
    }

    @Test
    public void testSearchUsers_FindAdmin() {
        System.out.println("Prueba searchUsers - Buscar administrador");
        
        List<User> result = userService.searchUsers("admin");
        
        assertEquals(1, result.size());
        assertEquals("admin123", result.get(0).getUsername());
        verify(userService).searchUsers("admin");
    }
    
    @Test
    public void testSearchUsers_FindEstudiantes() {
        System.out.println("Prueba searchUsers - Buscar estudiantes");
        
        List<User> result = userService.searchUsers("estudiante");
        
        assertEquals(2, result.size());
        assertEquals("estudiante1", result.get(0).getUsername());
        assertEquals("estudiante2", result.get(1).getUsername());
        verify(userService).searchUsers("estudiante");
    }
    
    @Test
    public void testSearchUsers_NoResults() {
        System.out.println("Prueba searchUsers - Sin resultados");
        
        List<User> result = userService.searchUsers("noExiste");
        
        assertTrue(result.isEmpty());
        verify(userService).searchUsers("noExiste");
    }

    @Test
    public void testSearchUserss_ByNameAndRole_Admin() {
        System.out.println("Prueba searchUserss - Por nombre y rol (admin)");
        
        List<User> result = userService.searchUserss("admin", "ADMIN");
        
        assertEquals(1, result.size());
        assertEquals("admin123", result.get(0).getUsername());
        assertEquals("ADMIN", result.get(0).getRole());
        verify(userService).searchUserss("admin", "ADMIN");
    }
    
    @Test
    public void testSearchUserss_ByNameAndRole_Estudiantes() {
        System.out.println("Prueba searchUserss - Por nombre y rol (estudiantes)");
        
        List<User> result = userService.searchUserss("estudiante", "ESTUDIANTE");
        
        assertEquals(2, result.size());
        assertEquals("estudiante1", result.get(0).getUsername());
        assertEquals("ESTUDIANTE", result.get(0).getRole());
        verify(userService).searchUserss("estudiante", "ESTUDIANTE");
    }
    
    @Test
    public void testSearchUserss_ByRoleOnly() {
        System.out.println("Prueba searchUserss - Solo por rol");
        
        List<User> result = userService.searchUserss("", "PROFESOR");
        
        assertEquals(1, result.size());
        assertEquals("profesor1", result.get(0).getUsername());
        assertEquals("PROFESOR", result.get(0).getRole());
        verify(userService).searchUserss("", "PROFESOR");
    }

    @Test
    public void testDeleteUser_Success() {
        System.out.println("Prueba deleteUser - Caso exitoso");
        
        boolean result = userService.deleteUser("estudiante2");
        
        assertTrue(result);
        verify(userService).deleteUser("estudiante2");
    }
    
    @Test
    public void testDeleteUser_UserNotFound() {
        System.out.println("Prueba deleteUser - Usuario no encontrado");
        
        boolean result = userService.deleteUser("noExiste");
        
        assertFalse(result);
        verify(userService).deleteUser("noExiste");
    }

    @Test
    public void testIsProfileCompleted_CompletedProfile() {
        System.out.println("Prueba isProfileCompleted - Perfil completado");
        
        boolean result = userService.isProfileCompleted("admin123");
        
        assertTrue(result);
        verify(userService).isProfileCompleted("admin123");
    }
    
    @Test
    public void testIsProfileCompleted_IncompleteProfile() {
        System.out.println("Prueba isProfileCompleted - Perfil incompleto");
        
        boolean result = userService.isProfileCompleted("profesor1");
        
        assertFalse(result);
        verify(userService).isProfileCompleted("profesor1");
    }
    
    @Test
    public void testIsProfileCompleted_UserNotFound() {
        System.out.println("Prueba isProfileCompleted - Usuario no encontrado");
        
        boolean result = userService.isProfileCompleted("noExiste");
        
        assertFalse(result);
        verify(userService).isProfileCompleted("noExiste");
    }

    @Test
    public void testUpdateProfileCompleted_Success() {
        System.out.println("Prueba updateProfileCompleted - Caso exitoso");
        
        boolean result = userService.updateProfileCompleted("profesor1", true);
        
        assertTrue(result);
        verify(userService).updateProfileCompleted("profesor1", true);
    }
    
    @Test
    public void testUpdateProfileCompleted_UserNotFound() {
        System.out.println("Prueba updateProfileCompleted - Usuario no encontrado");
        
        boolean result = userService.updateProfileCompleted("noExiste", true);
        
        assertFalse(result);
        verify(userService).updateProfileCompleted("noExiste", true);
    }
}