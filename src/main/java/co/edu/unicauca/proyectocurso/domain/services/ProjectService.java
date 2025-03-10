package co.edu.unicauca.proyectocurso.domain.services;

import co.edu.unicauca.proyectocurso.access.IProjectRepository;

import co.edu.unicauca.proyectocurso.domain.entities.Project;
import co.edu.unicauca.proyectocurso.domain.entities.ProjectState;
import java.util.List;
import java.util.UUID;


public class ProjectService {
    private final IProjectRepository repository;

    public ProjectService(IProjectRepository repository) {
        this.repository = repository;
    }

    // Método para registrar un proyecto
    public boolean registerProject(Project project, String nitEmpresa) {
        if (project == null || nitEmpresa == null || nitEmpresa.isBlank()) {
            return false;
        }
        return repository.save(project, nitEmpresa);
    }

    // Método para listar todos los proyectos
    public List<Project> listProjects() {
        return repository.findAll();
    }

    // Método para cambiar el estado de un proyecto
    public boolean changeProjectState(UUID projectId, ProjectState newState) {
        return repository.updateProjectState(projectId, newState);
    }

    // Método para filtrar proyectos por estado
    public List<Project> filterProjectsByState(ProjectState state) {
        return repository.findByState(state);
    }

    // Método para obtener un proyecto por su ID
    public Project getProjectById(UUID projectId) {
        return repository.findById(projectId);
    }
}