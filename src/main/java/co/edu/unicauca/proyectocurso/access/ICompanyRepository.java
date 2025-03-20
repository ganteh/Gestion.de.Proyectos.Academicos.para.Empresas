/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.proyectocurso.access;

import co.edu.unicauca.proyectocurso.domain.entities.Company;
import java.util.List;
/**
 *
 * @author yeixongec
 */
public interface ICompanyRepository {
    boolean save(Company company);
    List<Company> findAll();
    public boolean existsCompanyNIT(String companyNIT);
    
}