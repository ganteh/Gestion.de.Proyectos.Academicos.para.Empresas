package co.edu.unicauca.proyectocurso.domain.services;

import co.edu.unicauca.proyectocurso.access.DatabaseConnection;
import co.edu.unicauca.proyectocurso.domain.entities.User;

public class UserService {
    
    User user;
    
    public boolean registerUser(String username, String password, String role) {
        if (DatabaseConnection.userExists(username)) {
            System.out.println("❌ El usuario ya existe.");
            return false;
        }
        return DatabaseConnection.registerUser(username, password, role);
    }

    
    public String validarUsuario(String username, String password) {
        String miRol = DatabaseConnection.getUserRole(username, password); 
        if (miRol==null){
            return null;
        }
        user=DatabaseConnection.getUser(username);
        return  miRol;
    }

}
