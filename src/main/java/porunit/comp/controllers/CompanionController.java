package porunit.comp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import porunit.comp.data.dto.CompanionDTO;
import porunit.comp.data.dto.CompanionDataDTO;
import porunit.comp.services.AuthService;
import porunit.comp.services.CompanionService;

@RestController
@RequestMapping("/companion")
@RequiredArgsConstructor
public class CompanionController {

    private final CompanionService companionService;
    private final AuthService authService;


    @PostMapping
    public ResponseEntity<CompanionDTO> addCompanion(@RequestBody CompanionDTO companionDto) {
        CompanionDTO savedCompanion = companionService.addCompanion(companionDto);
        return ResponseEntity.ok(savedCompanion);
    }

    @PostMapping("/data")
    public ResponseEntity<CompanionDataDTO> addCompanionData(@RequestBody CompanionDataDTO companionDataDto) {
        CompanionDataDTO savedCompanionData = companionService.addCompanionData(companionDataDto);
        return ResponseEntity.ok(savedCompanionData);
    }

    @GetMapping
    public ResponseEntity<?> getAllCompanions(@RequestHeader("Authorization") String token) {
        String login = authService.getLoginFromHeader(token);
        return ResponseEntity.ok(companionService.getAllCompanionsIds(login));
    }

}
