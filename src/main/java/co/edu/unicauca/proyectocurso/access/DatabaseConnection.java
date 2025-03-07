package co.edu.unicauca.proyectocurso.access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/proyecto_db";
    private static final String USER = "root"; // Cambia esto si usas otro usuario
    private static final String PASSWORD = "CAMILO"; // Si tu MySQL tiene contraseña, agrégala aquí

    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver"); // Cargar el Driver
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("✅ Conexión exitosa a MySQL.");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                System.out.println("❌ Error al conectar a MySQL.");
            }
        }
        return connection;
    }
}
