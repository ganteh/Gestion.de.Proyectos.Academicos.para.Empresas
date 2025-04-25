package co.edu.unicauca.proyectocurso.access;

import co.edu.unicauca.proyectocurso.domain.entities.Company;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ibell
 */
public class CompanyRepositoryImpl implements ICompanyRepository {
    
    
        private Connection conn;

    
        public CompanyRepositoryImpl() {
        this.conn = DatabaseConnection.getConnection();
    }
    @Override
    public boolean existsCompanyNIT(String companyNIT) {
        String sql = "SELECT COUNT(*) FROM companies WHERE nit = ?";
        try (Connection conn = DatabaseConnection.getNewConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, companyNIT);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Retorna true si el NIT existe
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
public boolean save(Company company) {
        // Primero, obtenemos el id del usuario que crea la empresa.
        // Suponemos que el email (o username) del usuario está en la entidad Company (heredado de User).
        String getUserIdSql = "SELECT id FROM users WHERE username = ? AND role = 'Empresa'";
        String companySql = "INSERT INTO companies (nit, name, sector, contact_phone, contact_name, contact_lastname, contact_position, user_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getNewConnection();
             PreparedStatement getUserStmt = conn.prepareStatement(getUserIdSql)) {
             
            // Usamos el username de la empresa (que es el email/heredado)
            getUserStmt.setString(1, company.getUsername());
            ResultSet rs = getUserStmt.executeQuery();
            
            if (rs.next()) {
                int userId = rs.getInt("id");
                
                // Ahora, insertar los datos de la empresa, incluyendo el userId
                try (PreparedStatement companyStmt = conn.prepareStatement(companySql)) {
                    companyStmt.setString(1, company.getNit());
                    companyStmt.setString(2, company.getName());
                    companyStmt.setString(3, company.getSector());
                    companyStmt.setString(4, company.getContactPhone());
                    companyStmt.setString(5, company.getContactFirstName());
                    companyStmt.setString(6, company.getContactLastName());
                    companyStmt.setString(7, company.getContactPosition());
                    companyStmt.setInt(8, userId);
                    
                    int rowsAffected = companyStmt.executeUpdate();
                    return rowsAffected > 0;
                }
            } else {
                System.out.println("No se encontró un usuario con username: " + company.getUsername() + " y rol Empresa");
            }
            
        } catch (SQLException e) {
            System.err.println("Error al guardar la empresa: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public List<Company> findAll() {
        List<Company> companies = new ArrayList<>();
        String sql = "SELECT nit, name, sector, contact_phone, contact_name, contact_lastname, contact_position FROM companies";

        try (Connection conn = DatabaseConnection.getNewConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Company company = new Company();
                company.setNit(rs.getString("nit"));
                company.setName(rs.getString("name"));
                company.setSector(rs.getString("sector"));
                company.setContactPhone(rs.getString("contact_phone"));
                company.setContactFirstName(rs.getString("contact_name"));
                company.setContactLastName(rs.getString("contact_lastname"));
                company.setContactPosition(rs.getString("contact_position"));

                companies.add(company);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener las empresas: " + e.getMessage());
        }
        return companies;
    }
    
    public Company getCompanyByUserId(int userId) {
    Company company = null;
    String sql = "SELECT nit, name, sector, contact_phone, contact_name, contact_lastname, contact_position " +
                 "FROM companies WHERE user_id = ?";
    try (Connection conn = DatabaseConnection.getNewConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, userId);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                company = new Company();
                company.setNit(rs.getString("nit"));
                company.setName(rs.getString("name"));
                company.setSector(rs.getString("sector"));
                company.setContactPhone(rs.getString("contact_phone"));
                company.setContactFirstName(rs.getString("contact_name"));
                company.setContactLastName(rs.getString("contact_lastname"));
                company.setContactPosition(rs.getString("contact_position"));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return company;
}

    
}