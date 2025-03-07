package co.edu.unicauca.proyectocurso.domain.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Project {
    private UUID id;
    private String name;
    private String summary;
    private String objectives;
    private String description;
    private int maxMonths;
    private Double budget;
    private LocalDate date;
    private ProjectState state;
    private Company company;
    private List<String> comments;

    public Project(String name, String summary, String objectives, String description, 
                   int maxMonths, Double budget, Company company) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.summary = summary;
        this.objectives = objectives;
        this.description = description;
        this.maxMonths = maxMonths;
        this.budget = budget;
        this.date = LocalDate.now();
        this.state = ProjectState.RECEIVED;
        this.company = company;
        this.comments = new ArrayList<>();
    }

    public void addComment(String comment) {
        comments.add(comment);
    }

    public void changeState(ProjectState newState) {
        this.state = newState;
    }

    // Getters y Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }

    public String getObjectives() { return objectives; }
    public void setObjectives(String objectives) { this.objectives = objectives; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getMaxMonths() { return maxMonths; }
    public void setMaxMonths(int maxMonths) { this.maxMonths = maxMonths; }

    public Double getBudget() { return budget; }
    public void setBudget(Double budget) { this.budget = budget; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public ProjectState getState() { return state; }
    public void setState(ProjectState state) { this.state = state; }

    public Company getCompany() { return company; }
    public void setCompany(Company company) { this.company = company; }

    public List<String> getComments() { return comments; }
    public void setComments(List<String> comments) { this.comments = comments; }
}