
package co.edu.unicauca.proyectocurso.access;
import co.edu.unicauca.proyectocurso.domain.entities.Message;
import co.edu.unicauca.proyectocurso.domain.entities.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ibell
 */

public class MessageRepositoryImpl implements IMessageRepository {
    private List<Message> messages = new ArrayList<>();

    @Override
    public boolean save(Message message) {
        return messages.add(message);
    }

    @Override
    public List<Message> findBySenderAndReceiver(User sender, User receiver) {
        List<Message> filteredMessages = new ArrayList<>();
        for (Message message : messages) {
            if ((message.getSender().equals(sender) && message.getReceiver().equals(receiver)) ||
                (message.getSender().equals(receiver) && message.getReceiver().equals(sender))) {
                filteredMessages.add(message);
            }
        }
        return filteredMessages;
    }


}
