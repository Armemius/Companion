package porunit.comp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import porunit.comp.data.domain.Companion;
import porunit.comp.data.domain.CompanionData;
import porunit.comp.data.dto.CompanionDTO;
import porunit.comp.data.dto.CompanionDataDTO;
import porunit.comp.data.mapper.CompanionMapper;
import porunit.comp.data.mapper.CompanionDataMapper;
import porunit.comp.repositories.CompanionDataRepository;
import porunit.comp.repositories.CompanionRepository;

@RequiredArgsConstructor
@Service
public class CompanionService {

    private final CompanionRepository companionRepository;
    private final CompanionDataRepository companionDataRepository;
    private final CompanionMapper companionMapper;
    private final CompanionDataMapper companionDataMapper;


    public CompanionDTO addCompanion(CompanionDTO companionDto) {
        Companion companion = companionMapper.toEntity(companionDto);
        return companionMapper.toDto(companionRepository.save(companion));
    }

    public CompanionDataDTO addCompanionData(CompanionDataDTO companionDataDto) {
        CompanionData companionData = companionDataMapper.toEntity(companionDataDto);
        return companionDataMapper.toDto(companionDataRepository.save(companionData));
    }
}
