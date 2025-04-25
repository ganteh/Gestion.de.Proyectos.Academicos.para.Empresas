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
    try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        stmt.setString(1, user.getUsername());
        stmt.setString(2, user.getPassword());
        stmt.setString(3, user.getRole());
        
        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    // Asigna el id generado al objeto User
                    user.setId(id);
                }
            }
        }
        return rowsAffected > 0;
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
                    rs.getString("role"),
                    rs.getInt("id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // Método para registrar usuario
    public boolean registerUser(String username, String password, String role ,int id) {
        return save(new User(username, password, role, id));
   
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
    
public boolean updateUser(String oldUsername, String newUsername, String newPassword, String newRole) {
    String sql = "UPDATE users SET username = ?, password = ?, role = ? WHERE username = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, newUsername);
        stmt.setString(2, newPassword);
        stmt.setString(3, newRole);
        stmt.setString(4, oldUsername);
        int rowsUpdated = stmt.executeUpdate();
        return rowsUpdated > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

        public static User getUser(String username) {
    String sql = "SELECT id, username, password, role FROM users WHERE username = ?";
    try (Connection conn = getNewConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
         
         stmt.setString(1, username);    
         
         try (ResultSet rs = stmt.executeQuery()) {
             if (rs.next()) {
                 String uname = rs.getString("username");
                 String pwd = rs.getString("password");
                 String role = rs.getString("role");
                 int id = rs.getInt("id");
                 return new User(uname, pwd, role ,id);
             }
         }
    } catch (SQLException e) {
         e.printStackTrace();
    }
    return null;    
        }
    public List<User> searchUsers(String query) {
    List<User> 
    users = new ArrayList<>();
    String sql = "SELECT * FROM users WHERE username LIKE ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
         stmt.setString(1, "%" + query + "%");
         ResultSet rs = stmt.executeQuery();
         while(rs.next()){
            users.add(new User(
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("role"),
                rs.getInt("id")
            ));
         }
    } catch (SQLException e) {
         e.printStackTrace();
    }
    return users;
}
    public List<User> searchUserss(String name, String role) {
    List<User> users = new ArrayList<>();
    String sql = "SELECT * FROM users WHERE username LIKE ? AND role LIKE ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
         stmt.setString(1, "%" + name + "%");
         // Si se selecciona "Todos" o "Seleccione", no filtrar por rol
         if (role == null || role.equals("Seleccione") || role.equals("Todos")) {
             stmt.setString(2, "%");
         } else {
             stmt.setString(2, role);
         }
         ResultSet rs = stmt.executeQuery();
         while(rs.next()){
            users.add(new User(
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("role"),
                rs.getInt("id")
            ));
         }
    } catch (SQLException e) {
         e.printStackTrace();
    }
    return users;
}

    public boolean deleteUser(String username) {
    String sql = "DELETE FROM users WHERE username = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, username);
        int rowsDeleted = stmt.executeUpdate();
        return rowsDeleted > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
    public boolean isProfileCompleted(String username) {
    String sql = "SELECT profile_completed FROM users WHERE username = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
         stmt.setString(1, username);
         ResultSet rs = stmt.executeQuery();
         if (rs.next()) {
             return rs.getBoolean("profile_completed");
         }
    } catch (SQLException e) {
         e.printStackTrace();
    }
    return false; // Por defecto, si no se encuentra, se considera que no se completó el perfil.
}
    public boolean updateProfileCompleted(String username, boolean completed) {
    String sql = "UPDATE users SET profile_completed = ? WHERE username = ?";
    try (Connection conn = DatabaseConnection.getNewConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setBoolean(1, completed);
        stmt.setString(2, username);
        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}


}
