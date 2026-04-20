package api.api_prazo_certo.dto.response;

import api.api_prazo_certo.enums.UsuarioRole;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UsuarioResponseDto(
        UUID id,
        String nome,
        String email,
        String numeroOab,
        String cidade,
        UsuarioRole role
) {}
