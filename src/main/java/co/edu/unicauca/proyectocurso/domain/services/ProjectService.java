package co.edu.unicauca.proyectocurso.domain.services;

import co.edu.unicauca.proyectocurso.access.IProjectRepository;
import co.edu.unicauca.proyectocurso.domain.entities.Project;
import java.util.List;

public class ProjectService {
    private IProjectRepository repository;

    public ProjectService(IProjectRepository repository) {
        this.repository = repository;
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
}