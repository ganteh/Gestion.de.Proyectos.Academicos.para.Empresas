
package co.edu.unicauca.proyectocurso.access;
import co.edu.unicauca.proyectocurso.domain.entities.Message;
import co.edu.unicauca.proyectocurso.domain.entities.User;
import java.util.List;
/**
 *
 * @author ibell
 */


public interface IMessageRepository {
    boolean save(Message message);
    List<Message> findBySenderAndReceiver(User sender, User receiver);
}
