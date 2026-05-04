package api.api_prazo_certo.controller;

import api.api_prazo_certo.dto.response.DashboardResponseDto;
import api.api_prazo_certo.service.DashboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/juris-alerta/dashboard")
@RequiredArgsConstructor
@Tag(name = "Dashboard", description = "Endpoints para visualização de métricas e resumos do sistema.")
public class DashboardController {

    private final DashboardService dashboardService;

    @Operation(summary = "Obter Resumo Geral da API por meio do Dashboard.",
            description = "Retorna métricas gerais como total de processos, prazos pendentes e próximos vencimentos.")
    @GetMapping
    public ResponseEntity<DashboardResponseDto> resumoDashboard() {
        var resumoDashboard = dashboardService.obterResumoDashboard();
        return ResponseEntity.status(HttpStatus.OK).body(resumoDashboard);
    }
}
