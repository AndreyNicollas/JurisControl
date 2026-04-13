package api.api_prazo_certo.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record FeriadoRequestDto(
        @NotNull(message = "A data do feriado é obrigatória.")
        LocalDate dataFeriado,

        @NotBlank(message = "A descrição do feriado é obrigatória.")
        String descricao,

        @NotBlank(message = "A abrangência é obrigatória (Nacional, Estadual ou Municipal).")
        String abrangencia
) {}
