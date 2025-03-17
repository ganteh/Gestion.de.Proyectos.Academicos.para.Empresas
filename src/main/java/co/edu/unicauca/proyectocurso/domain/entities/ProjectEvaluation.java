package co.edu.unicauca.proyectocurso.domain.entities;
import java.util.UUID;
/**
 *
 * @author ibell
 */


public class ProjectEvaluation {
    private UUID id;
    private UUID projectId; // ID del proyecto evaluado
    private String companyUsername; // Username de la empresa que evalúa
    private int rating; // Calificación (1-5)
    private String comments; // Comentarios de la evaluación

    public ProjectEvaluation(UUID projectId, String companyUsername, int rating, String comments) {
        this.id = UUID.randomUUID();
        this.projectId = projectId;
        this.companyUsername = companyUsername;
        this.rating = rating;
        this.comments = comments;
    }

    // Getters y Setters
    public UUID getId() { return id; }
    public UUID getProjectId() { return projectId; }
    public String getCompanyUsername() { return companyUsername; }
    public int getRating() { return rating; }
    public String getComments() { return comments; }
}
