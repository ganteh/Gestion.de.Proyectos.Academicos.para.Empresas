
package co.edu.unicauca.proyectocurso.access;
import co.edu.unicauca.proyectocurso.domain.entities.CompanyNeed;
import java.util.List;
/**
 *
 * @author ibell
 */


public interface INeedRepository {
    
    /**
     * crea una nueva necesidad
     * @param need la necesidad para ser creada
     * @return true si se creo bien false si no
     */
    boolean create(CompanyNeed need);

    /**
     * lista todas las necesidades
     * @return la lista
     */
    List<CompanyNeed> list();

    /**
     * busca una necesidad por id
     * @param id el id que busco
     * @return la necesidad si la encuentra, si no null
     */
    CompanyNeed find(int id);

    /**
     * actuakliza una necesidad
     * @param need la necesidad con la info actualizada
     * @return true si se actualizo false si fallo
     */
    boolean update(CompanyNeed need);
}