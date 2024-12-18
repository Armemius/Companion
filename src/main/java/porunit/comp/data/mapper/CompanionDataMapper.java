package porunit.comp.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import porunit.comp.data.domain.CompanionData;
import porunit.comp.data.dto.CompanionDataDTO;

@Mapper(componentModel = "spring")
public interface CompanionDataMapper {

    @Mapping(source = "companion.companionId", target = "companionId")
    CompanionDataDTO toDto(CompanionData companionData);

    @Mapping(source = "companionId", target = "companion.companionId")
    CompanionData toEntity(CompanionDataDTO companionDataDto);
}

