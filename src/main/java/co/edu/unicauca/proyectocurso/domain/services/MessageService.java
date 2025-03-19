
package co.edu.unicauca.proyectocurso.domain.services;
import co.edu.unicauca.proyectocurso.access.IMessageRepository;
import co.edu.unicauca.proyectocurso.domain.entities.Message;
import co.edu.unicauca.proyectocurso.domain.entities.User;
import java.util.List;

/**
 *
 * @author ibell
 */

public class MessageService {
    private IMessageRepository repository;

    public MessageService(IMessageRepository repository) {
        this.repository = repository;
    }

    public boolean sendMessage(User sender, User receiver, String content) {
        Message message = new Message(sender, receiver, content);
        return repository.save(message);
    }

    public List<Message> getConversation(User user1, User user2) {
        return repository.findBySenderAndReceiver(user1, user2);
    }
}
