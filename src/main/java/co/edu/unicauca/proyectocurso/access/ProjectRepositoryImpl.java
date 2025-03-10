package co.edu.unicauca.proyectocurso.access;

import co.edu.unicauca.proyectocurso.domain.entities.Project;
import co.edu.unicauca.proyectocurso.domain.entities.ProjectState;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProjectRepositoryImpl implements IProjectRepository {

    @Override
    public boolean save(Project project, String nitEmpresa) {
        String sql = "INSERT INTO projects (id, name, description, state, company_nit, date, finalization_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, project.getId().toString());
            pstmt.setString(2, project.getName());
            pstmt.setString(3, project.getDescription());
            pstmt.setString(4, project.getState().toString());
            pstmt.setString(5, nitEmpresa);
            pstmt.setDate(6, Date.valueOf(project.getDate()));
            pstmt.setDate(7, new Date(project.getFinalizationDate().getTime()));
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Project> findAll() {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT * FROM projects";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Project project = new Project(
                    rs.getString("name"),
                    null, // summary (no está en la tabla)
                    null, // objectives (no está en la tabla)
                    rs.getString("description"),
                    0, // maxMonths (no está en la tabla)
                    0.0, // budget (no está en la tabla)
                    null // company (se puede cargar después)
                );
                project.setId(UUID.fromString(rs.getString("id")));
                project.setState(ProjectState.valueOf(rs.getString("state")));
                project.setDate(rs.getDate("date").toLocalDate());
                project.setFinalizationDate(rs.getDate("finalization_date"));
                projects.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }

    @Override
    public List<Project> findByState(ProjectState state) {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT * FROM projects WHERE state = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, state.toString());
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Project project = new Project(
                        rs.getString("name"),
                        null, // summary
                        null, // objectives
                        rs.getString("description"),
                        0, // maxMonths
                        0.0, // budget
                        null // company
                    );
                    project.setId(UUID.fromString(rs.getString("id")));
                    project.setState(ProjectState.valueOf(rs.getString("state")));
                    project.setDate(rs.getDate("date").toLocalDate());
                    project.setFinalizationDate(rs.getDate("finalization_date"));
                    projects.add(project);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }

    @Override
    public boolean updateProjectState(UUID projectId, ProjectState newState) {
        String sql = "UPDATE projects SET state = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newState.toString());
            pstmt.setString(2, projectId.toString());
            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Project findById(UUID projectId) {
        String sql = "SELECT * FROM projects WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, projectId.toString());
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Project project = new Project(
                        rs.getString("name"),
                        null, // summary
                        null, // objectives
                        rs.getString("description"),
                        0, // maxMonths
                        0.0, // budget
                        null // company
                    );
                    project.setId(UUID.fromString(rs.getString("id")));
                    project.setState(ProjectState.valueOf(rs.getString("state")));
                    project.setDate(rs.getDate("date").toLocalDate());
                    project.setFinalizationDate(rs.getDate("finalization_date"));
                    return project;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Si no se encuentra el proyecto, retornar null
    }
}