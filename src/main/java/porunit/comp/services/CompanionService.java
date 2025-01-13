package porunit.comp.services;

import jakarta.transaction.TransactionScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import porunit.comp.data.domain.Companion;
import porunit.comp.data.domain.CompanionData;
import porunit.comp.data.domain.User;
import porunit.comp.data.dto.CompanionDTO;
import porunit.comp.data.dto.CompanionDataDTO;
import porunit.comp.repositories.CompanionDataRepository;
import porunit.comp.repositories.CompanionRepository;
import porunit.comp.repositories.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CompanionService {

    private final CompanionRepository companionRepository;
    private final CompanionDataRepository companionDataRepository;
    private final UserRepository userRepository;

    public List<Integer> getAllCompanionsIds(String userLogin) {
        User user = userRepository.findByLogin(userLogin).orElseThrow(() -> new IllegalArgumentException("User not found"));
        List<Companion> companions = companionRepository.findAllByAuthorId(Math.toIntExact(user.getId()));
        return companions.stream().map(Companion::getCompanionId).collect(Collectors.toList());
    }

    @Transactional
    public CompanionDTO addCompanion(CompanionDTO companionDto, String login) {
        User user = userRepository.findFirstByLogin(login);
        Companion companion = toEntity(companionDto);
        companion.setAuthorId(Math.toIntExact(user.getId()));
        return toDto(companionRepository.save(companion));
    }

    public CompanionDataDTO addCompanionData(CompanionDataDTO companionDataDto) {
        Companion companion = companionRepository.findById(companionDataDto.getCompanionId())
                .orElseThrow(() -> new IllegalArgumentException("Companion not found with id: " + companionDataDto.getCompanionId()));
        var compData = companionDataRepository.findFirstByCompanion(companion);
        CompanionData companionData = toEntity(companionDataDto);
        if (compData != null) {
            companionData.setData(companionData.getData() + compData.getData());
            companionDataRepository.delete(compData);
        }
        companionData.setCompanion(companion);
        return toDto(companionDataRepository.save(companionData));
    }

    public CompanionDTO getCompanionById(Integer id) {
        Companion companion = companionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Companion not found with id: " + id));
        return toDto(companion);
    }

    public CompanionDataDTO getCompanionDataById(Integer id) {
        CompanionData companionData = companionDataRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("CompanionData not found with id: " + id));
        return toDto(companionData);
    }

    public void deleteCompanionById(Integer id) {
        if (!companionRepository.existsById(id)) {
            throw new IllegalArgumentException("Companion not found with id: " + id);
        }
        companionRepository.deleteById(id);
    }

    public void deleteCompanionDataById(Integer id) {
        if (!companionDataRepository.existsById(id)) {
            throw new IllegalArgumentException("CompanionData not found with id: " + id);
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
