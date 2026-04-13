package api.api_prazo_certo.mappers;

import api.api_prazo_certo.dto.request.FeriadoRequestDto;
import api.api_prazo_certo.dto.response.FeriadoResponseDto;
import api.api_prazo_certo.model.Feriado;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface FeriadoMapper {

    FeriadoResponseDto toResponse(Feriado feriado);

    @Mapping(target = "id", ignore = true)
    Feriado toEntity(FeriadoRequestDto feriadoRequestDto);
}
