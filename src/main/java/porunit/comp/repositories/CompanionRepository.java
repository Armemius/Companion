package porunit.comp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import porunit.comp.data.domain.Companion;

import java.util.List;

public interface CompanionRepository extends JpaRepository<Companion, Integer> {
    List<Companion> findAllByAuthorId(Integer authorId);
}