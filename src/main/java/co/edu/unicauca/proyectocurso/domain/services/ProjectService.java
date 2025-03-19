package co.edu.unicauca.proyectocurso.domain.services;

import co.edu.unicauca.proyectocurso.access.IProjectRepository;
import co.edu.unicauca.proyectocurso.access.ProjectRepositoryImpl;
import co.edu.unicauca.proyectocurso.domain.entities.Project;
import co.edu.unicauca.proyectocurso.domain.entities.ProjectState;
import java.util.List;

public class ProjectService extends Observado {
    private IProjectRepository repository = new ProjectRepositoryImpl(); ;

    public ProjectService(IProjectRepository repository) {
        this.repository = repository;
    }
    
    public ProjectService(){
        
    }

    public boolean registerProject(Project project, String nitEmpresa) {
        if (project == null || nitEmpresa == null || nitEmpresa.isBlank()) {
            return false;
        }
        return repository.save(project, nitEmpresa);
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