package co.edu.unicauca.proyectocurso.access;

import static co.edu.unicauca.proyectocurso.access.DatabaseConnection.getNewConnection;
import co.edu.unicauca.proyectocurso.domain.entities.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements IUserRepository {

    private Connection conn;

    public UserRepositoryImpl() {
        this.conn = DatabaseConnection.getConnection(); // Inicializa la conexión
    }

    @Override
    public boolean save(User user) {
        String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getRole());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                users.add(new User(
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("role")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // Método para registrar usuario
    public boolean registerUser(String username, String password, String role) {
        return save(new User(username, password, role));
    }

    // Método para validar usuario y obtener su rol
    public String getUserRole(String username, String password) {
        String sql = "SELECT role FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("role");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Método para verificar si un usuario existe
    public boolean userExists(String username) {
        String sql = "SELECT 1 FROM users WHERE username = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
        public static User getUser(String username) {
    String sql = "SELECT username, password, role FROM users WHERE username = ?";
    try (Connection conn = getNewConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
         
         stmt.setString(1, username);
         
         try (ResultSet rs = stmt.executeQuery()) {
             if (rs.next()) {
                 String uname = rs.getString("username");
                 String pwd = rs.getString("password");
                 String role = rs.getString("role");
                 return new User(uname, pwd, role);
             }
         }
    } catch (SQLException e) {
         e.printStackTrace();
    }
    return null;    }
    
}
