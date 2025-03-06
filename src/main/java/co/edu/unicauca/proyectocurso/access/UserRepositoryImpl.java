/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.proyectocurso.access;
import co.edu.unicauca.proyectocurso.domain.entities.User;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author yeixongec
 */
public class UserRepositoryImpl implements IUserRepository {
    private List<User> users = new ArrayList<>();

    @Override
    public boolean save(User user) {
        return users.add(user);
    }

    @Override
    public List<User> findAll() {
        return users;
    }
}
