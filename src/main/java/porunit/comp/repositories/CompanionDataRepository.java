package porunit.comp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import porunit.comp.data.domain.CompanionData;

public interface CompanionDataRepository extends JpaRepository<CompanionData, Integer> {
}