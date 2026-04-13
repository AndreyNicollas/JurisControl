package api.api_prazo_certo.mappers;

import api.api_prazo_certo.dto.request.ClienteRequestDto;
import api.api_prazo_certo.dto.response.ClienteResponseDto;
import api.api_prazo_certo.model.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {UsuarioMapper.class})
public interface ClienteMapper {

    ClienteResponseDto toResponse(Cliente cliente);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dataCadastro", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    Cliente toEntity(ClienteRequestDto clienteRequestDto);
}
