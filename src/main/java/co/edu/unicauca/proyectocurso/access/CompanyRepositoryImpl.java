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

    @Override
    public boolean save(Company company) {
        String sql = "INSERT INTO companies (nit, name, sector, contact_phone, contact_name, contact_lastname, contact_position) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getNewConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, company.getNit());
            pstmt.setString(2, company.getName());
            pstmt.setString(3, company.getSector());
            pstmt.setString(4, company.getContactPhone());
            pstmt.setString(5, company.getContactFirstName());
            pstmt.setString(6, company.getContactLastName());
            pstmt.setString(7, company.getContactPosition());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; // Retorna true si la inserción fue exitosa

        } catch (SQLException e) {
            System.err.println("Error al guardar la empresa: " + e.getMessage());
            return false;
        }
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
}