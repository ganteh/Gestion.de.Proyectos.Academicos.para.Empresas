
package co.edu.unicauca.figuras.domain.entities;

import java.util.List;

/**
 *
 * @author ibell
 */
public class Coordinator extends User {
    private String firstName;
    private String lastName;

    public Coordinator(String email, String password, String firstName, String lastName) {
        super(email, password);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public List<Project> evaluateProjects(List<Project> projects) {
        // Lógica de evaluación de proyectos
        return projects;
    }

    // Getters y setters
    public String getFullName() {
        return firstName + " " + lastName;
    }
}

