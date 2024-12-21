package porunit.comp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import porunit.comp.data.domain.DialogSession;
import porunit.comp.data.domain.User;

import java.util.List;

public interface DialogSessionRepository extends JpaRepository<DialogSession, Long> {

    List<DialogSession> findAllByUser(User user);
}
