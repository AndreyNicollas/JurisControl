package api.api_prazo_certo.dto.request;

import api.api_prazo_certo.enums.AbrangenciaFeriado;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record FeriadoRequestDto(
        @NotNull(message = "A data do feriado é obrigatória.")
        LocalDate dataFeriado,

        @NotBlank(message = "A descrição do feriado é obrigatória.")
        String descricao,

        @NotNull(message = "A abrangência é obrigatória (Nacional, Estadual ou Municipal).")
        AbrangenciaFeriado abrangencia,

        @NotBlank(message = "A UF é obrigatória.")
        String uf,

        @NotBlank(message = "O município é obrigatório.")
        String municipio
) {}
