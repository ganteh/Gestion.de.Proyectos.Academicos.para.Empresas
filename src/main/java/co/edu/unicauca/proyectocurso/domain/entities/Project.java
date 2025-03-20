package co.edu.unicauca.proyectocurso.domain.entities;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Date;

public class Project {

    private UUID id;
    private String name;
    private String description;
    private Date FinalizationDate;
    private LocalDate date;
    private ProjectState state;
    private Company company;
    private List<String> comments;
    private List<Student> students;
    private List<Task> tasks;
    private float budget;
    private int maxMonths;
    private String objectives;
    private String summary;

    public Project(String name, String summary, String objectives, String description,
            int maxMonths, float budget, LocalDate dueDate, String companyNIT) {
        this.id = UUID.randomUUID();
        this.name = (name != null) ? name : "Proyecto sin nombre";
        this.summary = summary; 
        this.description = (description != null) ? description : "Descripción no definida";
        this.date = LocalDate.now(); // Fecha de creación
        this.FinalizationDate = dueDate != null ? Date.from(dueDate.atStartOfDay(ZoneId.systemDefault()).toInstant()) : null; // Fecha de finalización
        this.state = ProjectState.RECEIVED;
        this.company = new Company(companyNIT); // Asume que Company tiene un constructor con NIT
        this.comments = new ArrayList<>();
        this.students = new ArrayList<>();
        this.budget = budget;
        this.maxMonths = maxMonths;
        this.objectives = (objectives != null) ? objectives : "";
    }

    public Project() {
        this.id = UUID.randomUUID();
        this.name = "Proyecto sin nombre";
        this.description = "Descripción no definida";
        this.date = LocalDate.now();
        this.state = ProjectState.RECEIVED;
        this.company = null; // Se puede asignar una empresa predeterminada si es necesario
        this.comments = new ArrayList<>();
        this.students = new ArrayList<>();
        this.tasks = new ArrayList<>();
        this.FinalizationDate = null; // Se puede definir una fecha predeterminada si aplica
    }

    public void addComment(String comment) {
        comments.add(comment);
    }

    public void changeState(ProjectState newState) {
        this.state = newState;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    // Getters y Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ProjectState getState() {
        return state;
    }

    public void setState(ProjectState state) {
        this.state = state;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Date getFinalizationDate() {
        return FinalizationDate;
    }

    public void setFinalizationDate(Date FinalizationDate) {
        this.FinalizationDate = FinalizationDate;
    }

    public float getBudget() {
        return budget;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }

    public int getMaxMonths() {
        return maxMonths;
    }

    public void setMaxMonths(int maxMonths) {
        this.maxMonths = maxMonths;
    }

    public String getObjectives() {
        return objectives;
    }

    public void setObjectives(String objectives) {
        this.objectives = objectives;
    }
    // Getter y Setter
    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }

}
