package co.edu.unicauca.proyectocurso.access;

import co.edu.unicauca.proyectocurso.domain.entities.*;
import co.edu.unicauca.proyectocurso.domain.services.CompanyService;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class ProjectRepositoryImpl implements IProjectRepository {
    
    private Connection connection;
    

    public ProjectRepositoryImpl(Connection conn) {
        this.connection = connection;
    }
    
    public ProjectRepositoryImpl(){}
    
    

 
    @Override
    public boolean save(Project project, String nitEmpresa) {
        String sql = "INSERT INTO projects (id, name, description, date, state, company_nit, budget, max_months, objectives) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getNewConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
           
            pstmt.setString(1, project.getId().toString());
            pstmt.setString(2, project.getName());
            pstmt.setString(3, project.getDescription());
            pstmt.setDate(4, Date.valueOf(project.getDate())); // Convertir LocalDate a SQL Date
            pstmt.setString(5, project.getState().toString()); // Enum convertido a String
            pstmt.setString(6, nitEmpresa);
            pstmt.setFloat(7, project.getBudget());
            pstmt.setInt(8, project.getMaxMonths());
            pstmt.setString(9, project.getObjectives());

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
        CompanyRepositoryImpl companyRepository = new CompanyRepositoryImpl();
        CompanyService companyService = new CompanyService(companyRepository);
        
        String sql = "SELECT id, name, description, date, state, company_nit, budget, max_months, objectives FROM projects";

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
                project.setBudget(rs.getFloat("budget"));
                project.setMaxMonths(rs.getInt("max_months"));
                project.setObjectives(rs.getString("objectives"));

                // Cargar la empresa asociada
                String companyNit = rs.getString("company_nit");
                Company auxCompany = companyService.findCompanyByNit(companyRepository.findAll(), companyNit);
                project.setCompany(auxCompany);
                projects.add(project);
            }
          
        } catch (SQLException e) {
            System.err.println("Error al obtener los proyectos: " + e.getMessage()); 
        }
        return projects;
    }

    @Override
    public boolean update(Project project) {
    String sql = "UPDATE projects SET name = ?, description = ?, date = ?, state = ?, company_nit = ? WHERE id = ?";

    try (Connection conn = DatabaseConnection.getNewConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, project.getName());
        pstmt.setString(2, project.getDescription());
        pstmt.setDate(3, Date.valueOf(project.getDate())); // Convertir LocalDate a SQL Date
        pstmt.setString(4, project.getState().toString()); // Enum convertido a String
        pstmt.setString(5, project.getCompany().getNit());
        pstmt.setString(6, project.getId().toString()); // UUID como String

        int rowsAffected = pstmt.executeUpdate();
        return rowsAffected > 0; // Retorna true si se actualizó correctamente

    } catch (SQLException e) {
        System.err.println("Error al actualizar el proyecto: " + e.getMessage());
        return false;
    }
}
}
