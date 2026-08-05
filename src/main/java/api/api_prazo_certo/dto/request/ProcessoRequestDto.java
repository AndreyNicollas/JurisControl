package api.api_prazo_certo.dto.request;

import api.api_prazo_certo.enums.StatusProcesso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record ProcessoRequestDto(
        @NotBlank(message = "O campo número do processo é obrigatório.")
        String numeroProcesso,

        @NotBlank(message = "A comarca é obrigatória.")
        String comarca,

        @NotBlank(message = "A vara é obrigatória.")
        String vara,

        @NotBlank(message = "A instância é obrigatória.")
        String instancia,

        @NotBlank(message = "O polo do cliente é obrigatório.")
        String poloCliente,

        @NotBlank(message = "O tipo de ação é obrigatório.")
        String tipoAcao,

        @NotNull(message = "O valor da causa é obrigatório.")
        BigDecimal valorCausa,

        @NotBlank(message = "O link do tribunal é obrigatório.")
        String linkTribunal,

//      StatusProcesso status,

//      @NotNull(message = "O usuário é obrigatório.")
//      UUID usuarioId,

        @NotNull(message = "O cliente é obrigatório.")
        UUID clienteId
) {}
