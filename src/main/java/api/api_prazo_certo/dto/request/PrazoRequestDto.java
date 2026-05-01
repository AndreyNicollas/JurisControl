package api.api_prazo_certo.dto.request;

import api.api_prazo_certo.enums.PrioridadePrazo;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record PrazoRequestDto(
        @NotBlank(message = "A descrição é obrigatória.")
        String descricao,

        @NotNull(message = "A data de início é obrigatória.")
        LocalDate dataInicio,

        @NotNull(message = "A quantidade de dias é obrigatória.")
        @Min(value = 1, message = "A quantidade de dias deve ser pelo menos 1.")
        Integer quantidadeDias,

        PrioridadePrazo prioridade,

        Boolean somenteDiasUteis,

        @NotNull(message = "O ID do processo é obrigatório.")
        UUID processoId
) {}
