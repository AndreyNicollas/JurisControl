package api.api_prazo_certo.config.security;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationDto(
        @NotBlank(message = "O email é obrigatório para o login.")
        String email,
        @NotBlank(message = "A senha é obrigatória para o login.")
        String senha
) {}
