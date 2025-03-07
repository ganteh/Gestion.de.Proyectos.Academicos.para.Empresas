package co.edu.unicauca.proyectocurso.access;

import co.edu.unicauca.proyectocurso.domain.entities.Project;
import java.util.ArrayList;
import java.util.List;

public class ProjectRepositoryImpl implements IProjectRepository {
    private List<Project> projects = new ArrayList<>();

    @Override
    public boolean save(Project project, String nitEmpresa) {
        return projects.add(project); // Simulación de guardado en memoria
    }

    @Override
    public List<Project> findAll() {
        return projects; // Retorna la lista simulada
    }
}
