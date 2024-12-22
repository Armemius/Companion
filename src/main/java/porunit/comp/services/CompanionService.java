package porunit.comp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import porunit.comp.data.domain.Companion;
import porunit.comp.data.domain.CompanionData;
import porunit.comp.data.dto.CompanionDTO;
import porunit.comp.data.dto.CompanionDataDTO;
import porunit.comp.repositories.CompanionDataRepository;
import porunit.comp.repositories.CompanionRepository;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CompanionService {

    private final CompanionRepository companionRepository;
    private final CompanionDataRepository companionDataRepository;

    public CompanionDTO addCompanion(CompanionDTO companionDto) {
        Companion companion = toEntity(companionDto);
        return toDto(companionRepository.save(companion));
    }

    public CompanionDataDTO addCompanionData(CompanionDataDTO companionDataDto) {
        Companion companion = companionRepository.findById(companionDataDto.getCompanionId())
                .orElseThrow(() -> new RuntimeException("Companion not found with id: " + companionDataDto.getCompanionId()));

        CompanionData companionData = toEntity(companionDataDto);
        companionData.setCompanion(companion);
        return toDto(companionDataRepository.save(companionData));
    }

    public CompanionDTO getCompanionById(Integer id) {
        Companion companion = companionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Companion not found with id: " + id));
        return toDto(companion);
    }

    public CompanionDataDTO getCompanionDataById(Integer id) {
        CompanionData companionData = companionDataRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CompanionData not found with id: " + id));
        return toDto(companionData);
    }

    public void deleteCompanionById(Integer id) {
        if (!companionRepository.existsById(id)) {
            throw new RuntimeException("Companion not found with id: " + id);
        }
        companionRepository.deleteById(id);
    }

    public void deleteCompanionDataById(Integer id) {
        if (!companionDataRepository.existsById(id)) {
            throw new RuntimeException("CompanionData not found with id: " + id);
        }
        companionDataRepository.deleteById(id);
    }

    private Companion toEntity(CompanionDTO dto) {
        Companion companion = new Companion();
        companion.setName(dto.getName());
        companion.setIcon(dto.getIcon());
        companion.setDataId(dto.getDataId());
        companion.setOpenAiAplId(dto.getOpenAiAplId());
        companion.setAuthorId(dto.getAuthorId());
        return companion;
    }

    private CompanionDTO toDto(Companion companion) {
        return CompanionDTO.builder()
                .name(companion.getName())
                .icon(companion.getIcon())
                .dataId(companion.getDataId())
                .openAiAplId(companion.getOpenAiAplId())
                .authorId(companion.getAuthorId())
                .build();
    }

    private CompanionData toEntity(CompanionDataDTO dto) {
        CompanionData companionData = new CompanionData();
        companionData.setData(dto.getData());
        return companionData;
    }

    private CompanionDataDTO toDto(CompanionData companionData) {
        CompanionDataDTO dto = new CompanionDataDTO();
        dto.setData(companionData.getData());
        dto.setCompanionId(companionData.getCompanion().getCompanionId());
        return dto;
    }
}
