package co.edu.unicauca.proyectocurso.access;

import co.edu.unicauca.proyectocurso.domain.entities.Project;
import co.edu.unicauca.proyectocurso.domain.entities.ProjectState;
import java.util.List;
import java.util.UUID;

public interface IProjectRepository {
    /**
     * Guarda un proyecto en la base de datos asociado a una empresa.
     * @param project Proyecto a registrar.
     * @param nitEmpresa NIT de la empresa asociada.
     * @return true si se guardó correctamente, false en caso contrario.
     */
    boolean save(Project project, String nitEmpresa);
    Project findById(UUID projectId); // Nuevo método
    /**
     * Obtiene la lista de todos los proyectos registrados.
     * @return Lista de proyectos.
     */
    List<Project> findAll();
    public List<Project> findByState(ProjectState state);
    public boolean updateProjectState(UUID projectId, ProjectState newState);
}
