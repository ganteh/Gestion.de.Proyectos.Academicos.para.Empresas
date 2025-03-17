
package co.edu.unicauca.proyectocurso.access;
import co.edu.unicauca.proyectocurso.domain.entities.Message;
import java.util.List;
/**
 *
 * @author ibell
 */


public interface IMessageRepository {
    boolean save(Message message);
    List<Message> findBySenderAndReceiver(String sender, String receiver);
}
