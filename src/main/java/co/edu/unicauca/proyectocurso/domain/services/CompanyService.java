package co.edu.unicauca.proyectocurso.domain.services;

import co.edu.unicauca.proyectocurso.access.CompanyRepositoryImpl;
import co.edu.unicauca.proyectocurso.access.ICompanyRepository;
import co.edu.unicauca.proyectocurso.domain.entities.Company;
import java.util.List;

/**
 *
 * @author ibell
 */
public class CompanyService {
    private final ICompanyRepository repository;

    public CompanyService(ICompanyRepository repository) {
        this.repository = repository;
    }

    public boolean registerCompany(String username, String password, String nit, 
                                  String name, String sector, String contactPhone, 
                                  String contactFirstName, String contactLastName, 
                                  String contactPosition, int id) {
        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            return false;
        }
        
        Company company = new Company(username, password, nit, name, sector, 
                                      contactPhone, contactFirstName, contactLastName, 
                                      contactPosition, id);
        return repository.save(company);
    }

    public List<Company> listCompanies() {
        return repository.findAll();
    }
    
    // Método original que busca por NIT en una lista dada
    public Company findCompanyByNit(List<Company> companies, String nit) {
        return companies.stream()
                .filter(company -> company.getNit().equals(nit))
                .findFirst()
                .orElse(null); // Retorna null si no encuentra coincidencias
    }

    // Nuevo método sobrecargado que usa el repositorio directamente
    public Company findCompanyByNit(String nit) {
        List<Company> companies = repository.findAll();
        return findCompanyByNit(companies, nit);
    }
    public boolean existsCompanyNIT(String nit) {
        return repository.existsCompanyNIT(nit);
    }
public Company getCompanyByUserId(int userId) {
    return ((CompanyRepositoryImpl) repository).getCompanyByUserId(userId);
}

}