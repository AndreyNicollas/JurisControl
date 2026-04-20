package api.api_prazo_certo.dto.request;

import api.api_prazo_certo.enums.UsuarioRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UsuarioRequestDto(
        @NotBlank(message = "O campo nome é obrigatório.")
        String nome,

        @NotBlank(message = "O campo email é obrigatório.")
        String email,

        @NotBlank(message = "O campo CPF é obrigatório.")
        String cpf,

        @NotBlank(message = "O campo número da OAB é obrigatório.")
        String numeroOab,

        @NotBlank(message = "A senha é obrigatória.")
        @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres.")
        String senha,

        @NotBlank(message = "O campo cidade é obrigatório.")
        String cidade,

        @NotNull(message = "A Role do Usuário deve ser informada!")
        UsuarioRole role
) {}