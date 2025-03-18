/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.proyectocurso.access;
import co.edu.unicauca.proyectocurso.domain.entities.User;
import java.util.List;
/**
 *
 * @author yeixongec
 */
public interface IUserRepository {
    boolean save(User user);
    List<User> findAll();
    
    
}
