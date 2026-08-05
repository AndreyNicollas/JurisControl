package api.api_prazo_certo.dto.response;

import api.api_prazo_certo.enums.AbrangenciaFeriado;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record FeriadoResponseDto(
        UUID id,
        LocalDate dataFeriado,
        String descricao,
        AbrangenciaFeriado abrangencia,
        String uf,
        String municipio,
        LocalDateTime criadoEm,
        LocalDateTime atualizadoEm
) {}
