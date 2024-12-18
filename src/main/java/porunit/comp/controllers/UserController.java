package porunit.comp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import porunit.comp.data.domain.User;
import porunit.comp.data.dto.UserDataDTO;
import porunit.comp.services.UserDataService;
import porunit.comp.services.UserService;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserDataService userDataService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDataDTO userDataDTO) {
        userDataService.saveUserData(userDataDTO);
        return ResponseEntity.ok("✅");
    }
}
