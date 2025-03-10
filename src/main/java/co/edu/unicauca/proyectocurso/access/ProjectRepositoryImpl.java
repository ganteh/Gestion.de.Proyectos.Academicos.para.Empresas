package co.edu.unicauca.proyectocurso.access;

import co.edu.unicauca.proyectocurso.domain.entities.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProjectRepositoryImpl implements IProjectRepository {

    @Override
    public boolean save(Project project, String nitEmpresa) {
        String sql = "INSERT INTO projects (id, name, description, date, state, company_nit) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
           
            pstmt.setString(1, project.getId().toString());
            pstmt.setString(2, project.getName());
            pstmt.setString(3, project.getDescription());
            pstmt.setDate(4, Date.valueOf(project.getDate())); // Convertir LocalDate a SQL Date
            pstmt.setString(5, project.getState().toString()); // Enum convertido a String
            pstmt.setString(6, nitEmpresa);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error al guardar el proyecto: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Project> findAll() {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT id, name, description, date, state, company_nit FROM projects";

        try (Connection conn = DatabaseConnection.getNewConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Project project = new Project();
                project.setId(UUID.fromString(rs.getString("id")));
                project.setName(rs.getString("name"));
                project.setDescription(rs.getString("description"));
                project.setDate(rs.getDate("date").toLocalDate());
                project.setState(ProjectState.valueOf(rs.getString("state"))); // Convertir String a Enum
                
                // Cargar la empresa asociada (solo si se requiere)
                String companyNit = rs.getString("company_nit");
                Company company = new Company(); // Aquí podrías usar un repositorio de empresa para buscarla
                company.setNit(companyNit);
                project.setCompany(company);
                
                projects.add(project);
            }
          
        } catch (SQLException e) {
            System.err.println("Error al obtener los proyectos: " + e.getMessage()); 
        }
        return projects;
    }
}
