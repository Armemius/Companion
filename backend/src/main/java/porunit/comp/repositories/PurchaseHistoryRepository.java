package porunit.comp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import porunit.comp.data.domain.PurchaseHistory;
import porunit.comp.data.domain.UserData;

import java.util.Arrays;
import java.util.List;

public interface PurchaseHistoryRepository extends JpaRepository<PurchaseHistory, Integer> {
    List<PurchaseHistory> findByUserData(UserData userData);
}
