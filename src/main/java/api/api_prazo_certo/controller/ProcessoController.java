package api.api_prazo_certo.controller;

import api.api_prazo_certo.dto.request.ProcessoRequestDto;
import api.api_prazo_certo.dto.response.ProcessoResponseDto;
import api.api_prazo_certo.service.ProcessoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api-prazo-certo/processos")
@RequiredArgsConstructor
public class ProcessoController {

    private final ProcessoService processoService;

    @PostMapping
    public ResponseEntity<ProcessoResponseDto> saveProcesso(@RequestBody @Validated ProcessoRequestDto processoRequestDto) {
        var saveProcesso = processoService.save(processoRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveProcesso);
    }

    @GetMapping
    public ResponseEntity<Page<ProcessoResponseDto>> findAllProcessos(@PageableDefault(size = 10) Pageable pageable) {
        var processos = processoService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(processos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProcessoResponseDto> updateProcesso(@PathVariable UUID id,
                                                              @RequestBody @Validated ProcessoRequestDto processoRequestDto) {
        var existingProcesso = processoService.update(id, processoRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(existingProcesso);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProcessoResponseDto> findProcessoById(@PathVariable UUID id) {
        var processo = processoService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(processo);
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<Page<ProcessoResponseDto>> findProcessosByClienteId(@PathVariable UUID clienteId,
                                                              @PageableDefault(size = 10) Pageable pageable) {
        var processos = processoService.findByIdCliente(clienteId, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(processos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProcessoById(@PathVariable UUID id) {
        processoService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
