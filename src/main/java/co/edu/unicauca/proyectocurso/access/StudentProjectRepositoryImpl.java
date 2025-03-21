package co.edu.unicauca.proyectocurso.access;



import co.edu.unicauca.proyectocurso.domain.entities.StudentProject;
import co.edu.unicauca.proyectocurso.domain.entities.Student;
import co.edu.unicauca.proyectocurso.domain.entities.Project;



import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StudentProjectRepositoryImpl implements IStudentProjectRepository {
    private  StudentRepositoryImpl studentRepo;
    private  ProjectRepositoryImpl projectRepo;

    public StudentProjectRepositoryImpl() {
        this.studentRepo = new StudentRepositoryImpl();
        this.projectRepo = new ProjectRepositoryImpl();
    }

    @Override
    public boolean save(StudentProject studentProject, String studentID, String projectID) {
        String sql = "INSERT INTO student_projects (id, student_id, project_id) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getNewConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentProject.getId());
            stmt.setString(2, studentID);
            stmt.setString(3, projectID);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<StudentProject> findAll() {
        List<StudentProject> studentsProjectsList = new ArrayList<>();

        String sql = "SELECT id, student_id, project_id, status FROM student_projects";

        try (Connection conn = DatabaseConnection.getNewConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                StudentProject studentsProject = new StudentProject();
                studentsProject.setId(rs.getInt("id"));
                studentsProject.setStudentID(rs.getString("student_id"));
                studentsProject.setProjectID(rs.getString("project_id"));
                studentsProject.setStatus(StudentProject.Status.valueOf(rs.getString("status")));
                
                // Obtener el estudiante asociado
                StudentRepositoryImpl studentRepo = new StudentRepositoryImpl();
                Student student = studentRepo.findById(UUID.fromString(rs.getString("student_id")));
                studentsProject.setStudent(student);

                // Obtener el proyecto asociado
                ProjectRepositoryImpl projectRepo = new ProjectRepositoryImpl();
                Project project = projectRepo.findById(UUID.fromString(rs.getString("project_id")));
                studentsProject.setProject(project);

                studentsProjectsList.add(studentsProject);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener las asignaciones de estudiantes a proyectos: " + e.getMessage());
        }

        return studentsProjectsList;
    }

    @Override
    public boolean update(StudentProject studentProject) {
        String sql = "UPDATE student_projects SET id = ?, status = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getNewConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, studentProject.getId());
            stmt.setString(2, studentProject.getStatus().toString());
            stmt.setInt(3, studentProject.getId());
            
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(UUID studentProjectID) {
        String sql = "DELETE FROM student_projects WHERE id = ?";
        try (Connection conn = DatabaseConnection.getNewConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, studentProjectID.toString());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
}