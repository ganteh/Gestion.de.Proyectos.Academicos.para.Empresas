package co.edu.unicauca.proyectocurso.domain.services;

import co.edu.unicauca.proyectocurso.access.DatabaseConnection;
import co.edu.unicauca.proyectocurso.access.UserRepositoryImpl;
import co.edu.unicauca.proyectocurso.domain.entities.User;
import java.util.List;

public class UserService {
    
    User user;
    
    private UserRepositoryImpl userRepository;

    public UserService(UserRepositoryImpl userRepository) {
        this.userRepository = userRepository;
    }

        public List<User> listUser() {
        return userRepository.findAll();
    }

    public boolean registerUser(String username, String password, String role) {
        if (userRepository.userExists(username)) {
            System.out.println("❌ El usuario ya existe.");
            return false;
        }
        return userRepository.registerUser(username, password, role);
    }
    
    public String validarUsuario(String username, String password) {
        String miRol = userRepository.getUserRole(username, password); 
        if (miRol==null){
            return null;
        }
        user=UserRepositoryImpl.getUser(username);
        return  miRol;
    }
    public boolean updateUser(String username, String newPassword, String newRole) {
        return userRepository.updateUser(username, newPassword, newRole);
    }
    public List<User> searchUsers(String query) {
        return userRepository.searchUsers(query);
    }
    
    public boolean deleteUser(String username){
        return userRepository.deleteUser(username);
    }
}
