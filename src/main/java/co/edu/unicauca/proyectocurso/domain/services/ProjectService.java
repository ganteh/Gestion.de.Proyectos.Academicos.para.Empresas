package co.edu.unicauca.proyectocurso.domain.services;

import co.edu.unicauca.proyectocurso.access.CompanyRepositoryImpl;
import co.edu.unicauca.proyectocurso.access.IProjectRepository;
import co.edu.unicauca.proyectocurso.access.ProjectRepositoryImpl;
import co.edu.unicauca.proyectocurso.domain.entities.Project;
import co.edu.unicauca.proyectocurso.domain.entities.ProjectState;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class ProjectService extends Observado {
    private IProjectRepository repository = new ProjectRepositoryImpl(); ;
    
    public ProjectService(IProjectRepository repository) {
        this.repository = repository;
    }
    
    public ProjectService(){
        
    }
    
    public boolean registerProject(String name, String summary, String objectives,
            String description, int maxMonths, float budget,
            LocalDate dueDate, String companyNIT) {
        // Validaciones
        if (name == null || name.isBlank()
                || description == null || description.isBlank()
                || maxMonths <= 0 || budget <= 0) {
            return false;
        }
        CompanyRepositoryImpl companyRepo = new CompanyRepositoryImpl();
    if (!companyRepo.existsCompanyNIT(companyNIT)) {
        System.out.println("El NIT ingresado no existe en la base de datos.");
        return false;  // Evita registrar el proyecto si el NIT no es válido
    }
        Project project = new Project(
                name, // Sin "String"
                summary,
                objectives,
                description,
                maxMonths,
                budget,
                dueDate,
                companyNIT
        );

        return repository.save(project, companyNIT);
    }
  
    
    public List<Project> listProjects() {
        return repository.findAll();
    }
    
    public List<Project> getPendingProjects() {
        return repository.findAll().stream()
                .filter(p -> p.getState() == ProjectState.RECEIVED)
                .toList();
    }
    
    public List<Project> getAcceptedProjects() {
        return repository.findAll().stream()
                .filter(p -> p.getState() == ProjectState.ACCEPTED)
                .toList();
    }
    
    public List<Project> getInExecutionProjects() {
        return repository.findAll().stream()
                .filter(p -> p.getState() == ProjectState.IN_EXECUTION)
                .toList();
    }

    public boolean validateProject(Project project) {
        return !project.getName().isBlank()
                && !project.getDescription().isBlank()
                && project.getBudget() > 0
                && project.getMaxMonths() > 0;
    }

    public void approveProject(Project project) {
        project.setState(ProjectState.ACCEPTED);
        repository.update(project);
        notifyObservers();  // Notifica a los observadores (la GUI)
    }
    

    public void rejectProject(Project project, String justification) {
        project.setState(ProjectState.REJECTED);
        repository.update(project);
        notifyObservers();
    }
   
}