package api.api_prazo_certo.mappers;

import api.api_prazo_certo.dto.request.UsuarioRequestDto;
import api.api_prazo_certo.dto.response.UsuarioResponseDto;
import api.api_prazo_certo.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UsuarioMapper {

    UsuarioResponseDto toResponse(Usuario usuario);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "criadoEm", ignore = true)
    @Mapping(target = "atualizadoEm", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "password", ignore = true)
    Usuario toEntity(UsuarioRequestDto usuarioRequestDto);
}
