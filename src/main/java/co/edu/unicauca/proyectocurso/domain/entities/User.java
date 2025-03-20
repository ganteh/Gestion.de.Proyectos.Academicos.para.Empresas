/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.proyectocurso.domain.entities;



/**
 *
 * @author ibell
 */
public class User {
    private String username;
    private String password;
    private String role; // Empresa, Coordinador, Estudiante

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }




    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    @Override
    public String toString() {
        return "User{" + "username=" + username + ", role=" + role + '}';
    }
}
