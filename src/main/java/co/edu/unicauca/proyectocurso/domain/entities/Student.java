
package co.edu.unicauca.proyectocurso.domain.entities;

/**
 *
 * @author ibell
 */
public class Student extends User {
    private String firstName;
    private String lastName;

    public Student(String email, String password, String firstName, String lastName) {
        super(email, password, "Estudiante");
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Getters y setters
    public String getFullName() {
        return firstName + " " + lastName;
    }
    @Override
    public String getRole() {
        return "Student";
    }
}
