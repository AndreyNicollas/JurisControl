package api.api_prazo_certo.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ProcessoResponseDto(
        UUID id,
        String numeroProcesso,
        String comarca,
        String vara,
        String instancia,
        String poloCliente,
        String tipoAcao,
        BigDecimal valorCausa,
        String linkTribunal,
        String status,
        LocalDateTime criadoEm,
        LocalDateTime atualizadoEm,
        UsuarioResponseDto usuario,
        ClienteResponseDto cliente
) {}
