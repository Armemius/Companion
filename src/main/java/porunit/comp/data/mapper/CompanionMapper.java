package porunit.comp.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import porunit.comp.data.domain.Companion;
import porunit.comp.data.dto.CompanionDTO;

@Mapper(componentModel = "spring")
public interface CompanionMapper {

    CompanionDTO toDto(Companion companion);

    @Mapping(target = "sessions", ignore = true)
    @Mapping(target = "comments", ignore = true)
    Companion toEntity(CompanionDTO companionDto);
}
