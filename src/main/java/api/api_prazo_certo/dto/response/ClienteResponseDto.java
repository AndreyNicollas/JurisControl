package api.api_prazo_certo.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ClienteResponseDto(
        UUID id,
        String nome,
        String cpfCnpj,
        String telefone,
        String email,
        UsuarioResponseDto usuario
) {}
