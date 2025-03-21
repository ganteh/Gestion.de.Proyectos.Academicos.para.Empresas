package co.edu.unicauca.proyectocurso.domain.services;

import co.edu.unicauca.proyectocurso.access.CompanyRepositoryImpl;
import co.edu.unicauca.proyectocurso.access.IProjectRepository;
import co.edu.unicauca.proyectocurso.domain.entities.Project;
import co.edu.unicauca.proyectocurso.domain.entities.ProjectState;
import co.edu.unicauca.proyectocurso.domain.entities.Student;
import co.edu.unicauca.proyectocurso.domain.entities.StudentProject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProjectService extends Observado {
    private final IProjectRepository repository;

    // Constructor con inyección de dependencias
    public ProjectService(IProjectRepository repository) {
        this.repository = repository;
    }

    // Constructor sin parámetros (uso con repositorio por defecto)
    public ProjectService() {
        this.repository = new co.edu.unicauca.proyectocurso.access.ProjectRepositoryImpl();
    }

    /**
     * Registra un nuevo proyecto en la base de datos
     * @param name
     * @param summary
     * @param objectives
     * @param description
     * @param dueDate
     * @param maxMonths
     * @param budget
     * @param companyNIT
     * @return 
     */
    public boolean registerProject(String name, String summary, String objectives,
                                   String description, int maxMonths, float budget,
                                   LocalDate dueDate, String companyNIT) {
        // Validaciones básicas
        if (name == null || name.isBlank() ||
            description == null || description.isBlank() ||
            maxMonths <= 0 || budget <= 0) {
            System.out.println("❌ Error: Datos inválidos para registrar el proyecto.");
            return false;
        }

        // Validación de existencia de la empresa
        CompanyRepositoryImpl companyRepo = new CompanyRepositoryImpl();
        if (!companyRepo.existsCompanyNIT(companyNIT)) {
            System.out.println("❌ Error: El NIT ingresado no existe en la base de datos.");
            return false;
        }

        // Creación del proyecto
        Project project = new Project(
                name, summary, objectives, description, maxMonths, budget, dueDate, companyNIT
        );

        // Guardar el proyecto
        return repository.save(project, companyNIT);
    }

    /**
     * Obtiene todos los proyectos
     * @return 
     */
    public List<Project> listProjects() {
        return repository.findAll();
    }

    /**
     * Obtiene un proyecto por su ID
     * @param projectId
     * @return 
     */
    public Optional<Project> findById(UUID projectId) {
        return repository.findAll().stream()
                .filter(p -> p.getId().equals(projectId))
                .findFirst();
    }

    /**
     * Obtiene proyectos con estado "RECEIVED"
     * @return 
     */
    public List<Project> getPendingProjects() {
        return repository.findAll().stream()
                .filter(p -> p.getState() == ProjectState.RECEIVED)
                .toList();
    }

    /**
     * Obtiene proyectos con estado "ACCEPTED"
     * @return 
     */
    public List<Project> getAcceptedProjects() {
        return repository.findAll().stream()
                .filter(p -> p.getState() == ProjectState.ACCEPTED)
                .toList();
    }

    /**
     * Obtiene proyectos con estado "IN_EXECUTION"
     * @return 
     */
    public List<Project> getInExecutionProjects() {
        return repository.findAll().stream()
                .filter(p -> p.getState() == ProjectState.IN_EXECUTION)
                .toList();
    }

    /**
     * Valida si un proyecto es válido antes de guardarlo
     * @param project
     * @return 
     */
    public boolean validateProject(Project project) {
        return project != null &&
               !project.getName().isBlank() &&
               !project.getDescription().isBlank() &&
               project.getBudget() > 0 &&
               project.getMaxMonths() > 0;
    }

    /**
     * Aprueba un proyecto cambiando su estado a "ACCEPTED"
     * @param project
     */
    public void approveProject(Project project) {
        if (project != null) {
            project.setState(ProjectState.ACCEPTED);
            repository.update(project);
            notifyObservers(); // Notificar cambios a la UI
        }
    }

    /**
     * Elimina un proyecto por ID
     * @param projectId
     * @return 
     */
    public boolean delete(UUID projectId) {
        if (projectId == null) {
            return false;
        }
        return repository.delete(projectId);
    }

    /**
     * Rechaza un proyecto con una justificación
     * @param project
     * @param justification
     */
    public void rejectProject(Project project, String justification) {
        if (project != null && justification != null && !justification.isBlank()) {
            project.setState(ProjectState.REJECTED);
            repository.update(project);
            notifyObservers();
        }
    }
    public List<Project> findProjectsByCompanyNIT(String nit) {
        return repository.findProjectsByCompanyNIT(nit);
    }
    
    public boolean updateProject(Project project) {
        // Validaciones adicionales (opcional)
        if (project == null || project.getId() == null) {
            System.err.println("Error: Proyecto inválido para actualizar");
            return false;
        }

        try {
            return repository.update(project); // Llama al método del repositorio
        } catch (Exception e) {
            System.err.println("Error en el servicio al actualizar proyecto: " + e.getMessage());
            return false;
        }
    }
    
    public void inExecuteValidation(StudentProject studentProject, ArrayList<Student> students){
        Project proyecto = repository.findById(UUID.fromString(studentProject.getProjectID()));
                  if (students.size() == 1) {
                    proyecto.setState(ProjectState.IN_EXECUTION);
                        repository.update(proyecto);
                        notifyObservers();
                }
    }
}
