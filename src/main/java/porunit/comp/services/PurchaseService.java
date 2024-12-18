package porunit.comp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import porunit.comp.data.domain.*;
import porunit.comp.data.dto.PurchaseDTO;
import porunit.comp.repositories.PurchaseHistoryRepository;
import porunit.comp.repositories.PurchaseRepository;
import porunit.comp.repositories.UserDataRepository;
import porunit.comp.repositories.UserRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final UserRepository userRepository;
    private final UserDataRepository userDataRepository;
    private final PurchaseRepository purchaseRepository;
    private final PurchaseHistoryRepository purchaseHistoryRepository;

    public void savePurchase(String login, PurchaseDTO purchaseDTO) {

        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        UserData userData = userDataRepository.findByUser(user)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Purchase purchase = new Purchase();
        purchase.setDate(LocalDateTime.now());
        purchase.setPrice(purchaseDTO.getPrice());
        purchase.setStatus(purchaseDTO.getStatus());
        purchase = purchaseRepository.save(purchase);

        PurchaseHistory purchaseHistory = new PurchaseHistory();
        purchaseHistory.setUserData(userData);
        purchaseHistory.setPurchase(purchase);

        purchaseHistoryRepository.save(purchaseHistory);
    }

    public List<PurchaseDTO> getAllPurchasesByLogin(String login) {
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        UserData userData = userDataRepository.findByUser(user)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return purchaseHistoryRepository.findByUserData(userData).stream()
                .map(purchaseHistory -> {
                    Purchase purchase = purchaseHistory.getPurchase();
                    return PurchaseDTO.builder()
                            .price(purchase.getPrice())
                            .status(purchase.getStatus())
                            .date(purchase.getDate().format(formatter))
                            .build();
                })
                .collect(Collectors.toList());
    }
}

