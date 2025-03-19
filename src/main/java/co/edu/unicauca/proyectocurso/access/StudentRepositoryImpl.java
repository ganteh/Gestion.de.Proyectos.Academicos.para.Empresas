/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.proyectocurso.access;
import static co.edu.unicauca.proyectocurso.access.DatabaseConnection.getNewConnection;
import co.edu.unicauca.proyectocurso.domain.entities.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yeixongec
 */
public class StudentRepositoryImpl implements IStudentRepository {

    private Connection conn;

    public StudentRepositoryImpl() {
        this.conn = DatabaseConnection.getConnection();
    }

    /**
     * Guarda los datos del estudiante vinculándolo con un usuario ya registrado
     * en `users`.
     */
    
    @Override
    public boolean save(Student student) {
        String getUserIdSql = "SELECT id FROM users WHERE username = ? AND role = 'Estudiante'";
        String studentSql = "INSERT INTO students (id, user_id, first_name, last_name, program, project_id) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement getUserIdStmt = conn.prepareStatement(getUserIdSql)) {
            // Obtener el ID del usuario registrado en `users`
            getUserIdStmt.setString(1, student.getUsername());
            ResultSet rs = getUserIdStmt.executeQuery();

            if (rs.next()) {
                int userId = rs.getInt("id");

                // Guardar los datos adicionales en `students`
                try (PreparedStatement studentStmt = conn.prepareStatement(studentSql)) {
                    studentStmt.setString(1, student.getId().toString());
                    studentStmt.setInt(2, userId);
                    studentStmt.setString(3, student.getFirstName());
                    studentStmt.setString(4, student.getLastName());
                    studentStmt.setString(5, student.getProgram());
                     // Si el project_id es "null" como string, pasamos un valor NULL real
                    if (student.getProjectID().equalsIgnoreCase("null")) {
                    studentStmt.setNull(6, java.sql.Types.VARCHAR);
                    } else {
                    studentStmt.setString(6, student.getProjectID());
                    }
                    return studentStmt.executeUpdate() > 0;
                }
            } else {
                System.out.println("No se encontró un usuario con el username: " + student.getUsername());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    // Método para registrar estudiante
    public boolean registerStudent(String username, String password, String firstName, String lastName, String program, String project_id) {
        return save(new Student(username, password, firstName, lastName,program ,project_id));
    }

    /**
     * Obtiene un estudiante por su username.
     */
    public Student findByUsername(String username) {
        String sql = """
            SELECT u.username, u.password, s.first_name, s.last_name
            FROM users u
            JOIN students s ON u.id = s.user_id
            WHERE u.username = ?;
        """;

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Student(
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("program"),
                        rs.getString("project_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    
    
    @Override
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                students.add(new Student(
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("program"),
                    rs.getString("project_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
    // Método para verificar si un estudiante existe
    public boolean estudanteExists(String username) {
        String sql = "SELECT 1 " +
"        FROM users u" +
"        JOIN students s ON u.id = s.user_id" +
"        WHERE u.username = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean update(Student student) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
