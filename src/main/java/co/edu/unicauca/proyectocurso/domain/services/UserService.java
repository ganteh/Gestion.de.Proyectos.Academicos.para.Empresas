/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.proyectocurso.domain.services;

import co.edu.unicauca.proyectocurso.access.IUserRepository;
import co.edu.unicauca.proyectocurso.domain.entities.User;
import java.util.List;
/**
 *
 * @author yeixongec
 */
public class UserService {
    
    private IUserRepository repository;

    public UserService(IUserRepository repository) {
        this.repository = repository;
    }

    public boolean registerUser(String username, String password, String role) {
        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            return false; // Validación básica
        }
        User user = new User(username, password, role);
        return repository.save(user);
    }

    public List<User> listUsers() {
        return repository.findAll();
    }
}
