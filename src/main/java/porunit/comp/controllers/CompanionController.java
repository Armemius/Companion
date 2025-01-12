package porunit.comp.controllers;

import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<CompanionDTO> addCompanion(@RequestBody CompanionDTO companionDto,
                                                     @RequestHeader("Authorization") String token) {
        String login = authService.getLoginFromHeader(token);
        CompanionDTO savedCompanion = companionService.addCompanion(companionDto, login);
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

    @GetMapping("/{id}")
    public ResponseEntity<CompanionDTO> getCompanionById(@PathVariable Integer id) {
        CompanionDTO companionDTO = companionService.getCompanionById(id);
        return ResponseEntity.ok(companionDTO);
    }

    @GetMapping("/data/{id}")
    public ResponseEntity<CompanionDataDTO> getCompanionDataById(@PathVariable Integer id) {
        CompanionDataDTO companionDataDTO = companionService.getCompanionDataById(id);
        return ResponseEntity.ok(companionDataDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompanionById(@PathVariable Integer id) {
        companionService.deleteCompanionById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/data/{id}")
    public ResponseEntity<Void> deleteCompanionDataById(@PathVariable Integer id) {
        companionService.deleteCompanionDataById(id);
        return ResponseEntity.noContent().build();
    }
}
