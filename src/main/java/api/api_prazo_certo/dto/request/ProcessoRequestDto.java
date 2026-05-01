package api.api_prazo_certo.dto.request;

import api.api_prazo_certo.enums.StatusProcesso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ProcessoRequestDto(
        @NotBlank(message = "O campo número do processo é obrigatório.")
        String numeroProcesso,

        @NotBlank(message = "A comarca é obrigatória.")
        String comarca,

        @NotBlank(message = "A vara é obrigatória.")
        String vara,

        @NotBlank(message = "O tipo de ação é obrigatório.")
        String tipoAcao,

        @NotNull(message = "O cliente é obrigatório.")
        UUID clienteId
) {}
