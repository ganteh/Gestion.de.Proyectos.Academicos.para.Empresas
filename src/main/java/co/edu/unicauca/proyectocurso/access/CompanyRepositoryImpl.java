/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.proyectocurso.access;

import co.edu.unicauca.proyectocurso.domain.entities.Company;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author yeixongec
 */
public class CompanyRepositoryImpl implements ICompanyRepository {
    private List<Company> companies = new ArrayList<>();

    @Override
    public boolean save(Company company) {
        return companies.add(company);
    }

    @Override
    public List<Company> findAll() {
        return companies;
    }
}