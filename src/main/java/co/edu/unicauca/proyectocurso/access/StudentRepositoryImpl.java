/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.proyectocurso.access;

import co.edu.unicauca.proyectocurso.domain.entities.Project;
import co.edu.unicauca.proyectocurso.domain.entities.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
                    studentStmt.setString(1, student.getIdd().toString());
                    studentStmt.setInt(2, userId);
                    studentStmt.setString(3, student.getFirstName());
                    studentStmt.setString(4, student.getLastName());
                    studentStmt.setString(5, student.getProgram());
                    // Si el project_id es "null" como string, pasamos un valor NULL real
                    if (student.getProjectID() == null || student.getProjectID().equalsIgnoreCase("null")) {
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

    /**
     * Método para registrar estudiante
     */
    public boolean registerStudent(String username, String password, String firstName, String lastName, String program, String project_id, int id) {
        return save(new Student(username, password, firstName, lastName, program, project_id, id));
    }

    /**
     * Obtiene un estudiante por su username.
     */
    public Student findByUsername(String username) {
        String sql = """
            SELECT u.username, u.password, s.id, s.first_name, s.last_name, s.program, s.project_id
            FROM users u
            JOIN students s ON u.id = s.user_id
            WHERE u.username = ?;
        """;
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Student(
                    rs.getString("id"),
                    rs.getString("username"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("program"),
                    rs.getString("project_id"),
                    rs.getInt("id")
                );
            }
            return null; // Retorna null si no se encuentra el estudiante
        } catch (SQLException e) {
            e.printStackTrace();
            return null; // Manejar la excepción adecuadamente
        }
    }

    /**
     * Obtiene el ID del estudiante por su nombre de usuario
     * @param username Usuario del
     */
    /**
      * Obtiene el ID del estudiante por su nombre de usuario
      */
     public String getStudentIdByUsername(String username) {
         String sql = "SELECT s.id FROM students s JOIN users u ON s.user_id = u.id WHERE u.username = ?";
         try (PreparedStatement stmt = conn.prepareStatement(sql)) {
             stmt.setString(1, username);
             ResultSet rs = stmt.executeQuery();
             if (rs.next()) {
                 String studentId = rs.getString("id");
                 System.out.println("ID del estudiante encontrado: [" + studentId + "]");
                 return studentId;
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }
         return null;
     }


    @Override
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        String sql = """
            SELECT s.id, u.username, u.password, s.first_name, s.last_name, s.program, s.project_id
            FROM students s
            JOIN users u ON s.user_id = u.id
        """;
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                students.add(new Student(
                    rs.getString("id"),
                    rs.getString("username"),
                    
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("program"),
                    rs.getString("project_id"),
                    rs.getInt("id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    /**
     * Método para verificar si un estudiante existe
     */
    public boolean estudanteExists(String username) {
        String sql = """
            SELECT 1
            FROM users u
            JOIN students s ON u.id = s.user_id
            WHERE u.username = ?
        """;
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Obtiene todos los proyectos disponibles
     */
    public List<Object[]> findAvailableProjects() {
        List<Object[]> projects = new ArrayList<>();
        String sql = """
            SELECT p.id AS project_id, p.date, e.name AS company_name,
               p.name AS project_name, p.description
               FROM projects p
               JOIN companies e ON p.company_nit = e.nit
                WHERE p.state = 'ACCEPTED'
            """;

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                projects.add(new Object[]{
                    rs.getString("project_id"),
                    rs.getDate("date"),
                    rs.getString("company_name"),
                    rs.getString("project_name"),
                    rs.getString("description")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }

    /**
     * Inserta una relación entre estudiante y proyecto  studentId, String projectId, String status) {
    try {
        conn.setAutoCommit(false);
        
        // Primero verifica si el estudiante existe
        String checkSql = "SELECT 1 FROM students WHERE id = ?";
        try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
            checkStmt.setString(1, studentId);
            ResultSet rs = checkStmt.executusando una transacción
     */
    public boolean insertStudentProject(String studentId, String projectId, String status) {
    try {
        conn.setAutoCommit(false);
        
        // Primero verifica si el estudiante existe
        String checkSql = "SELECT 1 FROM students WHERE id = ?";
        try (PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
            checkStmt.setString(1, studentId);
            ResultSet rs = checkStmt.executeQuery();
            if (!rs.next()) {
                System.out.println("El estudiante con ID [" + studentId + "] no existe");
                conn.rollback();
                return false;
            }
        }
        
        // Luego verifica si el proyecto existe
        String checkProjectSql = "SELECT 1 FROM projects WHERE id = ?";
        try (PreparedStatement checkProjStmt = conn.prepareStatement(checkProjectSql)) {
            checkProjStmt.setString(1, projectId);
            ResultSet rs = checkProjStmt.executeQuery();
            if (!rs.next()) {
                System.out.println("El proyecto con ID [" + projectId + "] no existe");
                conn.rollback();
                return false;
            }
        }
        
        // Verifica si el estudiante ya está postulado a este proyecto
        String checkExistingSql = "SELECT 1 FROM student_projects WHERE student_id = ? AND project_id = ?";
        try (PreparedStatement checkExistingStmt = conn.prepareStatement(checkExistingSql)) {
            checkExistingStmt.setString(1, studentId);
            checkExistingStmt.setString(2, projectId);
            ResultSet rs = checkExistingStmt.executeQuery();
            if (rs.next()) {
                System.out.println("El estudiante con ID [" + studentId + "] ya está postulado al proyecto [" + projectId + "]");
                conn.rollback();
                return false;
            }
        }
        
        // Si no existe una postulación previa, haz la inserción
        String insertSql = "INSERT INTO student_projects (student_id, project_id, status) VALUES (?, ?, ?)";
        try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
            insertStmt.setString(1, studentId);
            insertStmt.setString(2, projectId);
            insertStmt.setString(3, status);
            int result = insertStmt.executeUpdate();
            conn.commit();
            return result > 0;
        }
    } catch (SQLException e) {
        try {
            conn.rollback();
        } catch (SQLException rollbackEx) {
            rollbackEx.printStackTrace();
        }
        e.printStackTrace();
        return false;
    } finally {
        try {
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
    public boolean isStudentAlreadyApplied(String studentId, String projectId) {
    String sql = "SELECT COUNT(*) FROM student_projects WHERE student_id = ? AND project_id = ?";
    
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, studentId);
        stmt.setString(2, projectId);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return rs.getInt(1) > 0; // Devuelve true si ya existe la postulación
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}


    @Override
    public boolean update(Student student) {
        String sql = "UPDATE students SET first_name = ?, last_name = ?, program = ?, project_id = ? WHERE id = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, student.getFirstName());
            stmt.setString(2, student.getLastName());
            stmt.setString(3, student.getProgram());
            if (student.getProjectID() == null) {
                stmt.setNull(4, Types.VARCHAR);
            } else {
                stmt.setString(4, student.getProjectID());
            }
            stmt.setString(5, student.getIdd().toString());
            
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Student findById(UUID studentId) {
        String sql = "SELECT s.id, u.username, s.first_name, s.last_name, s.program, s.project_id " +
                     "FROM students s JOIN users u ON s.user_id = u.id WHERE s.id = ?";
        
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, studentId.toString());
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new Student(
                    rs.getString("id"),
                    rs.getString("username"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("program"),
                    rs.getString("project_id"),
                    rs.getInt("id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Student> findStudentsByProjectId(String projectId) {
        ArrayList<Student> students = new ArrayList<>();
        String sql = "SELECT s.id, s.first_name, s.last_name, s.program " +
                     "FROM students s " +
                     "JOIN student_projects sp ON s.id = sp.student_id " +
                     "WHERE sp.project_id = ?";

        try (Connection conn = DatabaseConnection.getNewConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, projectId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Student student = new Student(
                    rs.getString("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("program"),
                    projectId,
                    rs.getInt("id")
                );
                students.add(student);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener estudiantes del proyecto: " + e.getMessage());
        }

        return students;
    }
}
