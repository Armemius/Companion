package porunit.comp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import porunit.comp.data.domain.Companion;

public interface CompanionRepository extends JpaRepository<Companion, Integer> {
}