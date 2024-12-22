package porunit.comp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import porunit.comp.data.dto.CompanionDTO;
import porunit.comp.data.dto.CompanionDataDTO;
import porunit.comp.services.CompanionService;

@RestController
@RequestMapping("/companion")
public class CompanionController {

    private final CompanionService companionService;

    @Autowired
    public CompanionController(CompanionService companionService) {
        this.companionService = companionService;
    }

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
}
