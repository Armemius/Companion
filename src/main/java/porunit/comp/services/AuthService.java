package porunit.comp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import porunit.comp.data.domain.Role;
import porunit.comp.data.domain.User;
import porunit.comp.data.dto.UserDTO;
import porunit.comp.jwt.JwtProvider;
import porunit.comp.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    public void register(UserDTO userDTO) {
        User user = User.builder()
                .username(userDTO.getUsername())
                .login(userDTO.getLogin())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .userRole(Role.USER)
                .build();
        userRepository.save(user);
    }

    public String getLoginFromHeader(String authorizationHeader) {
        String jwtToken = authorizationHeader.substring(7);
        return jwtProvider.getUsernameFromJwt(jwtToken);
    }
}