package api.api_prazo_certo.controller;

import api.api_prazo_certo.dto.request.FeriadoRequestDto;
import api.api_prazo_certo.dto.response.FeriadoResponseDto;
import api.api_prazo_certo.service.FeriadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/juris-alerta/feriados")
@RequiredArgsConstructor
@Tag(name = "Feriados", description = "Endpoints para gerenciamento de feriados.")
public class FeriadoController {

    private final FeriadoService feriadoService;

    @Operation(summary = "Cadastrar novo Feriado.",
            description = "Cadastra um novo feriado ou período de suspensão de prazos.")
    @PostMapping
    public ResponseEntity<FeriadoResponseDto> saveFeriado(@RequestBody @Validated FeriadoRequestDto feriadoRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(feriadoService.save(feriadoRequestDto));
    }

    @Operation(summary = "Listar todos os Feriados.",
            description = "Retorna uma lista paginada de todos os feriados cadastrados.")
    @GetMapping
    public ResponseEntity<Page<FeriadoResponseDto>> findAllFeriados(@ParameterObject @PageableDefault(size = 10) Pageable pageable) {
        var feriados = feriadoService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(feriados);
    }

    @Operation(summary = "Atualizar Feriado.",
            description = "Atualiza os dados de um feriado existente através do seu ID.")
    @PutMapping("/{id}")
    public ResponseEntity<FeriadoResponseDto> updateFeriado(@PathVariable UUID id,
                                                            @RequestBody @Validated FeriadoRequestDto feriadoRequestDto) {
        var existingFeriado = feriadoService.update(id, feriadoRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(existingFeriado);
    }

    @Operation(summary = "Deletar Feriado.",
            description = "Remove um feriado do sistema através do seu ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeriadoById(@PathVariable UUID id) {
        feriadoService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
