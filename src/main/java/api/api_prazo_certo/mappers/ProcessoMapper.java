package api.api_prazo_certo.mappers;

import api.api_prazo_certo.dto.request.ProcessoRequestDto;
import api.api_prazo_certo.dto.response.ProcessoResponseDto;
import api.api_prazo_certo.model.Processo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {ClienteMapper.class})
public interface ProcessoMapper {

    ProcessoResponseDto toResponse(Processo processo);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCadastro", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "cliente", ignore = true)
    Processo toEntity(ProcessoRequestDto processoRequestDto);
}
