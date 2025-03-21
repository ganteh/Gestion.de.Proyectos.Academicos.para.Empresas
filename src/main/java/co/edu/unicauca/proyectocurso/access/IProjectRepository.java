package co.edu.unicauca.proyectocurso.access;

import co.edu.unicauca.proyectocurso.domain.entities.Project;
import java.util.List;
import java.util.UUID;

public interface IProjectRepository {

    /**
     * Guarda un proyecto en la base de datos asociado a una empresa.
     *
     * @param project Proyecto a registrar.
     * @param nitEmpresa NIT de la empresa asociada.
     * @return true si se guardó correctamente, false en caso contrario.
     */
    boolean save(Project project, String nitEmpresa);

    /**
     * Obtiene la lista de todos los proyectos registrados.
     *
     * @return Lista de proyectos.
     */
    List<Project> findAll();

    /**
     *
     * @param project Proyecto a actualizar
     * @return True si el proyecto se actualizo correctamente, en caso contrario
     * False
     */
    public boolean update(Project project);

    public List<Project> findProjectsByCompanyNIT(String nit);

    boolean delete(UUID projectId);

    public Project findById(UUID fromString);
}
