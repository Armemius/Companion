package porunit.comp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import porunit.comp.services.BalanceService;

@RestController
@RequestMapping("/balance")
@RequiredArgsConstructor
public class BalanceController {

    private final BalanceService balanceService;

    @GetMapping
    public ResponseEntity<Integer> getBalance(@RequestHeader("Authorization") String token) {
        try {
            int balance = balanceService.getBalance(token);
            return ResponseEntity.ok(balance);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> addBalance(@RequestHeader("Authorization") String token,
                                             @RequestParam int amount) {
        try {
            balanceService.addBalance(token, amount);
            return ResponseEntity.ok("Баланс успешно пополнен.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
