package co.edu.unicauca.proyectocurso.domain.entities;

import java.util.Date;

/**
 *
 * @author ibell
 */
public class CompanyNeed {

    private int id;
    private String name;
    private String description;
    private String status;
    private Date registrationDate;

    //constructor
    public CompanyNeed() {
    }

    public CompanyNeed(int id, String name, String description, String status, Date registrationDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.registrationDate = registrationDate;
    }

    //setters and getters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString() {
        return "CompanyNeed{" + "id=" + id + ", name='" + name + '\'' + ", description='" + description + '\'' + ", status='" + status + '\'' + ", registrationDate=" + registrationDate + '}';
    }
}
