
package co.edu.unicauca.proyectocurso.access;
import co.edu.unicauca.proyectocurso.domain.entities.CompanyNeed;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ibell
 */



public class NeedRepository implements INeedRepository {
    private final List<CompanyNeed> needs;

    public NeedRepository() {
        this.needs = new ArrayList<>();
    }

    @Override
    public boolean create(CompanyNeed need) {
        return needs.add(need);
    }

    @Override
    public List<CompanyNeed> list() {
        return new ArrayList<>(needs);
    }

    @Override
    public CompanyNeed find(int id) {
        return needs.stream()
                .filter(need -> need.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean update(CompanyNeed updatedNeed) {
        for (int i = 0; i < needs.size(); i++) {
            if (needs.get(i).getId() == updatedNeed.getId()) {
                needs.set(i, updatedNeed);
                return true;
            }
        }
        return false;
    }
}
