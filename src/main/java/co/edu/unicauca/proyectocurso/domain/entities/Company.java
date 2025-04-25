
package co.edu.unicauca.proyectocurso.domain.entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ibell
 */
public class Company extends User {
    private int id;
    private String nit;
    private String name;
    private String sector;
    private String contactPhone;
    private String contactFirstName;
    private String contactLastName;
    private String contactPosition;
    private List<Project> projects;

    public Company(String User, String password, String nit, String name, String sector, 
                   String contactPhone, String contactFirstName, String contactLastName, 
                   String contactPosition , int id) {
        super(User, password, "Empresa", id);
        this.nit = nit;
        this.name = name;
        this.sector = sector;
        this.contactPhone = contactPhone;
        this.contactFirstName = contactFirstName;
        this.contactLastName = contactLastName;
        this.contactPosition = contactPosition;
        this.projects = new ArrayList<>();
    } 
public Company(){
    super("defaultEmail@example.com", "defaultPassword", "Empresa", 0);
    // Puedes inicializar otros campos si es necesario
    this.nit = "";
    this.name = "";
    this.sector = "";
    this.contactPhone = "";
    this.contactFirstName = "";
    this.contactLastName = "";
    this.contactPosition = "";
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

