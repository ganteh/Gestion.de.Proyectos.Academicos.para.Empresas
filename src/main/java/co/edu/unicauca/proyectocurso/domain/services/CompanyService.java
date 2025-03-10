
package co.edu.unicauca.proyectocurso.domain.services;
import co.edu.unicauca.proyectocurso.access.ICompanyRepository;
import co.edu.unicauca.proyectocurso.domain.entities.Company;
import java.util.List;

/**
 *
 * @author yeixongec
 */

public class CompanyService {
    private final ICompanyRepository repository;

    public CompanyService(ICompanyRepository repository) {
        this.repository = repository;
    }

    public boolean registerCompany(String username, String password, String nit, 
                                  String name, String sector, String contactPhone, 
                                  String contactFirstName, String contactLastName, 
                                  String contactPosition) {
        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            return false;
        }
        
        Company company = new Company(username, password, nit, name, sector, 
                                      contactPhone, contactFirstName, contactLastName, 
                                      contactPosition);
        return repository.save(company);
    }

    public List<Company> listCompanies() {
        return repository.findAll();
    }
}