package co.edu.unicauca.proyectocurso.access;

import co.edu.unicauca.proyectocurso.domain.entities.User;
import java.sql.*;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/proyecto_db";
    private static final String USER = "root"; 
    private static final String PASSWORD = "CAMILO"; 

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("✅ Conexión exitosa a MySQL.");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                System.out.println("❌ Error al conectar a MySQL.");
            }
        }
        return connection;
    }

    public static Connection getNewConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("No se encontró el driver de MySQL.", e);
        }
    }

    // Método para registrar usuario
    public static boolean registerUser(String username, String password, String role) {
        String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
        return executeUpdate(sql, username, password, role);
    }

    // Método para validar usuario y obtener su rol
    public static String getUserRole(String username, String password) {
        String sql = "SELECT role FROM users WHERE username = ? AND password = ?";
        try (ResultSet rs = executeQuery(sql, username, password)) {
            if (rs != null && rs.next()) {
                return rs.getString("role");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Método para verificar si un usuario existe
    public static boolean userExists(String username) {
        String sql = "SELECT 1 FROM users WHERE username = ?";
        try (ResultSet rs = executeQuery(sql, username)) {
            return rs != null && rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Método genérico para ejecutar INSERT, UPDATE, DELETE
    private static boolean executeUpdate(String sql, Object... params) {
        try (Connection conn = getNewConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método genérico para ejecutar SELECT
    private static ResultSet executeQuery(String sql, Object... params) {
        try {
            Connection conn = getNewConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }

            return stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static User getUser(String username, String password) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static User getUser(String username) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
