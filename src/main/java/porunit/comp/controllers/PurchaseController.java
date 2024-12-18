package porunit.comp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import porunit.comp.data.dto.PurchaseDTO;
import porunit.comp.services.AuthService;
import porunit.comp.services.PurchaseService;

import java.util.List;

@RestController
@RequestMapping("/purchase")
@RequiredArgsConstructor
public class PurchaseController {
    private final PurchaseService purchaseService;
    private final AuthService authService;

    @PostMapping
    public ResponseEntity<?> savePurchase(@RequestBody PurchaseDTO purchaseRequest,
                                          @RequestHeader("Authorization") String token) {
        String login = authService.getLoginFromHeader(token);

        purchaseService.savePurchase(login, purchaseRequest);

        return ResponseEntity.ok("Purchase saved successfully");
    }

    @GetMapping
    public ResponseEntity<List<PurchaseDTO>> getAllPurchases(@RequestHeader("Authorization") String token) {
        String login = authService.getLoginFromHeader(token);

        List<PurchaseDTO> purchases = purchaseService.getAllPurchasesByLogin(login);

        return ResponseEntity.ok(purchases);
    }

}
