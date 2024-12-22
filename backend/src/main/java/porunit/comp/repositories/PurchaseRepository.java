package porunit.comp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import porunit.comp.data.domain.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
}
