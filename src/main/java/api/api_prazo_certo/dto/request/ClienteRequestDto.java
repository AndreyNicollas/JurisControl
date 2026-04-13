package api.api_prazo_certo.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ClienteRequestDto(
        @NotBlank(message = "O campo nome é obrigatório.")
        String nome,

        @NotBlank(message = "O campo CPF ou CNPJ é obrigatório.")
        String cpfCnpj,

        @NotBlank(message = "O campo telefone é obrigatório.")
        String telefone,

        @NotBlank(message = "O campo email é obrigatório.")
        String email,

        @NotNull(message = "O ID do usuário é obrigatório")
        UUID usuarioId
) {}
