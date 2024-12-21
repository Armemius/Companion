package porunit.comp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import porunit.comp.data.domain.DialogSession;
import porunit.comp.data.domain.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllBySession(DialogSession session);
}
