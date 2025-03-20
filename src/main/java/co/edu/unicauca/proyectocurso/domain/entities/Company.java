
package co.edu.unicauca.proyectocurso.domain.entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ibell
 */
public class Company extends User {
    private String nit;
    private String name;
    private String sector;
    private String contactPhone;
    private String contactFirstName;
    private String contactLastName;
    private String contactPosition;
    private List<Project> projects;

    public Company(String email, String password, String nit, String name, String sector, 
                   String contactPhone, String contactFirstName, String contactLastName, 
                   String contactPosition) {
        super(email, password, "Empresa");
        this.nit = nit;
        this.name = name;
        this.sector = sector;
        this.contactPhone = contactPhone;
        this.contactFirstName = contactFirstName;
        this.contactLastName = contactLastName;
        this.contactPosition = contactPosition;
        this.projects = new ArrayList<>();
    } 
    // Constructor con NIT
    public Company(String nit) {
        super("default@empresa.com", "password123", "Empresa"); // Valores por defecto
        this.nit = nit;
        // Inicializa otros campos si es necesario
    }
    
    //Constructor genérico con valores predeterminados
    public Company() {
        super("default@empresa.com", "password123", "Empresa"); // Usuario genérico
        this.nit = "000000000";
        this.name = "Empresa Desconocida";
        this.sector = "No especificado";
        this.contactPhone = "0000000000";
        this.contactFirstName = "Nombre";
        this.contactLastName = "Apellido";
        this.contactPosition = "Cargo";
        this.projects = new ArrayList<>();
    }
    

    public void addProject(Project project) {
        projects.add(project);
    }

    // Getters y setters

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactFirstName() {
        return contactFirstName;
    }

    public void setContactFirstName(String contactFirstName) {
        this.contactFirstName = contactFirstName;
    }

    public String getContactLastName() {
        return contactLastName;
    }

    public void setContactLastName(String contactLastName) {
        this.contactLastName = contactLastName;
    }

    public String getContactPosition() {
        return contactPosition;
    }

    public void setContactPosition(String contactPosition) {
        this.contactPosition = contactPosition;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
    
    @Override
    public String toString() {
        return "Empresa{" + "username=" + getUsername() + ", nit=" + nit + ", sector=" + sector + '}';
    }


    
}

