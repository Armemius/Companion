package porunit.comp.services;

import org.springframework.stereotype.Service;
import porunit.comp.data.domain.User;
import porunit.comp.repositories.UserRepository;

@Service
public class BalanceService {

    private final UserRepository userRepository;
    private final AuthService authService;

    public BalanceService(UserRepository userRepository, AuthService authService) {
        this.userRepository = userRepository;
        this.authService = authService;
    }


    public int getBalance(String token) {
        String login = authService.getLoginFromHeader(token);
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new IllegalArgumentException("Пользователь не найден"));
        return user.getBalance();
    }


    public void addBalance(String token, int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Сумма должна быть больше 0");
        }

        String login = authService.getLoginFromHeader(token);
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new IllegalArgumentException("Пользователь не найден"));

        user.setBalance(user.getBalance() + amount);
        userRepository.save(user);
    }
}

