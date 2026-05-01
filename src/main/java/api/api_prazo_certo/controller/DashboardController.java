package api.api_prazo_certo.controller;

import api.api_prazo_certo.dto.response.DashboardResponseDto;
import api.api_prazo_certo.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api-prazo-certo/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public ResponseEntity<DashboardResponseDto> resumoDashboard() {
        var resumoDashboard = dashboardService.obterResumoDashboard();
        return ResponseEntity.status(HttpStatus.OK).body(resumoDashboard);
    }
}
