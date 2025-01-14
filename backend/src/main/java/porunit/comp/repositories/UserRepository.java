package porunit.comp.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import porunit.comp.data.domain.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findFirstByLogin(String login);

    boolean existsByLogin(String login);

    Optional<User> findByLogin(String login);
}
