package api.api_prazo_certo.dto.response;

import api.api_prazo_certo.enums.PrioridadePrazo;
import api.api_prazo_certo.enums.StatusPrazo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PrazoResponseDto(
        UUID id,
        String descricao,
        LocalDateTime dataInicio,
        Integer quantidadeDias,
        LocalDateTime dataVencimento,
        LocalDateTime dataConclusaoReal,
        PrioridadePrazo prioridade,
        Boolean somenteDiasUteis,
        StatusPrazo status,
        String observacao,
        LocalDateTime criadoEm,
        LocalDateTime atualizadoEm,

//      @JsonIgnoreProperties({"comarca", "vara", "tipoAcao", "status", "dataCadastro", "cliente"})
        ProcessoResponseDto processo
) {}
