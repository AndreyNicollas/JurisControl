package api.api_prazo_certo.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record FeriadoResponseDto(
        UUID id,
        LocalDate dataFeriado,
        String descricao,
        String abrangencia
) {}
