package co.edu.unicauca.proyectocurso.access;

import co.edu.unicauca.proyectocurso.domain.entities.User;
import java.sql.*;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/proyecto_db";
    private static final String USER = "root"; 

    private static final String PASSWORD = "CAMILO"; //contraseña respectiva 
 

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

    



}
