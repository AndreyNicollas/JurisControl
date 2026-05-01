package api.api_prazo_certo.mappers;

import api.api_prazo_certo.dto.request.PrazoRequestDto;
import api.api_prazo_certo.dto.response.PrazoResponseDto;
import api.api_prazo_certo.model.Prazo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {ProcessoMapper.class})
public interface PrazoMapper {

    PrazoResponseDto toResponse(Prazo prazo);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCriacao", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "processo", ignore = true)
    @Mapping(target = "dataVencimento", ignore = true)
    Prazo toEntity(PrazoRequestDto prazoRequestDto);
}
