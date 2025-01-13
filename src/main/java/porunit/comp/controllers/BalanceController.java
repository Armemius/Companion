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
        int balance = balanceService.getBalance(token);
        return ResponseEntity.ok(balance);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addBalance(@RequestHeader("Authorization") String token,
                                             @RequestParam int amount) {
        balanceService.addBalance(token, amount);
        return ResponseEntity.ok("Баланс успешно пополнен.");
    }
}
