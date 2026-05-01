package api.api_prazo_certo.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record DashboardResponseDto(
        Long totalClientes,
        Long totalProcessosAtivos,
        Long prazosUrgentes,
        Long prazosVencidos,
        List<PrazoResponseDto> proximosPrazos
) {}
