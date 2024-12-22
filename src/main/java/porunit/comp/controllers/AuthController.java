package porunit.comp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import porunit.comp.data.domain.Role;
import porunit.comp.data.dto.AuthResponseDTO;
import porunit.comp.data.dto.UserDTO;
import porunit.comp.exceptions.UsernameTakenException;
import porunit.comp.jwt.JwtProvider;
import porunit.comp.repositories.UserRepository;
import porunit.comp.services.AuthService;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {
    private final UserRepository userRepository;
    private final AuthService authService;
    private final AuthenticationManager authManager;
    private final JwtProvider jwtProvider;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        Authentication authentication = authManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        userDTO.getLogin(), userDTO.getPassword()
                ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        return ResponseEntity.ok(new AuthResponseDTO(token,
                userRepository.findFirstByLogin(userDTO.getLogin()).getUserRole()
        ));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserDTO userDTO) throws UsernameTakenException {
        if (userRepository.existsByLogin(userDTO.getLogin())) {
            throw new UsernameTakenException("Username taken");
        }
        authService.register(userDTO);
        Authentication authentication = authManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        userDTO.getLogin(), userDTO.getPassword()
                ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        return ResponseEntity.ok(new AuthResponseDTO(token, Role.USER));
    }

    @GetMapping("/role")
    public ResponseEntity<?> getRole(
            @RequestHeader("Authorization") String authorizationHeader){
        Role role = userRepository.findFirstByLogin(authService.getLoginFromHeader(authorizationHeader)).getUserRole();
        return ResponseEntity.ok(role);
    }
    @GetMapping("/username")
    public ResponseEntity<?> getUsernameFromToken(
            @RequestHeader("Authorization") String authorizationHeader) {
        return ResponseEntity.ok(authService.getLoginFromHeader(authorizationHeader));
    }

}
