package co.edu.unicauca.proyectocurso.access;

import co.edu.unicauca.proyectocurso.domain.entities.Project;
import java.util.List;

public interface IProjectRepository {
    /**
     * Guarda un proyecto en la base de datos asociado a una empresa.
     * @param project Proyecto a registrar.
     * @param nitEmpresa NIT de la empresa asociada.
     * @return true si se guardó correctamente, false en caso contrario.
     */
    boolean save(Project project, String nitEmpresa);

    /**
     * Obtiene la lista de todos los proyectos registrados.
     * @return Lista de proyectos.
     */
    List<Project> findAll();
}
