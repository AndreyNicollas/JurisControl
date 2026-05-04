package api.api_prazo_certo.controller;

import api.api_prazo_certo.dto.request.ProcessoRequestDto;
import api.api_prazo_certo.dto.response.ProcessoResponseDto;
import api.api_prazo_certo.service.ProcessoService;
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
@RequestMapping("/v1/juris-alerta/processos")
@RequiredArgsConstructor
@Tag(name = "Processos", description = "Endpoints para gerenciamento dos processos judiciais vinculados aos clientes.")
public class ProcessoController {

    private final ProcessoService processoService;

    @Operation(summary = "Cadastrar novo Processo.",
            description = "Cria um novo processo judicial vinculado a um cliente.")
    @PostMapping
    public ResponseEntity<ProcessoResponseDto> saveProcesso(@RequestBody @Validated ProcessoRequestDto processoRequestDto) {
        var saveProcesso = processoService.save(processoRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveProcesso);
    }

    @Operation(summary = "Listar todos os Processos.",
            description = "Retorna uma lista paginada de todos os processos do advogado autenticado.")
    @GetMapping
    public ResponseEntity<Page<ProcessoResponseDto>> findAllProcessos(@ParameterObject @PageableDefault(size = 10) Pageable pageable) {
        var processos = processoService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(processos);
    }

    @Operation(summary = "Atualizar Processo.",
            description = "Atualiza os dados de um processo existente através do seu ID.")
    @PutMapping("/{id}")
    public ResponseEntity<ProcessoResponseDto> updateProcesso(@PathVariable UUID id,
                                                              @RequestBody @Validated ProcessoRequestDto processoRequestDto) {
        var existingProcesso = processoService.update(id, processoRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(existingProcesso);
    }

    @Operation(summary = "Buscar Processo por ID.",
            description = "Retorna os detalhes de um processo específico através do seu ID.")
    @GetMapping("/{id}")
    public ResponseEntity<ProcessoResponseDto> findProcessoById(@PathVariable UUID id) {
        var processo = processoService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(processo);
    }

    @Operation(summary = "Listar Processos por Cliente.",
            description = "Retorna uma lista paginada de processos vinculados a um cliente específico.")
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<Page<ProcessoResponseDto>> findProcessosByClienteId(@PathVariable UUID clienteId,
                                                                              @ParameterObject @PageableDefault(size = 10) Pageable pageable) {
        var processos = processoService.findByIdCliente(clienteId, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(processos);
    }

    @Operation(summary = "Deletar Processo.",
            description = "Remove um processo do sistema através do seu ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProcessoById(@PathVariable UUID id) {
        processoService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
