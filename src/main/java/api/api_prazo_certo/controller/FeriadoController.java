package api.api_prazo_certo.controller;

import api.api_prazo_certo.dto.request.FeriadoRequestDto;
import api.api_prazo_certo.dto.response.FeriadoResponseDto;
import api.api_prazo_certo.service.FeriadoService;
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
@RequestMapping("/api-prazo-certo/feriados")
@RequiredArgsConstructor
public class FeriadoController {

    private final FeriadoService feriadoService;

    @PostMapping
    public ResponseEntity<FeriadoResponseDto> saveFeriado(@RequestBody @Validated FeriadoRequestDto feriadoRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(feriadoService.save(feriadoRequestDto));
    }

    @GetMapping
    public ResponseEntity<Page<FeriadoResponseDto>> findAllFeriados(@PageableDefault(size = 10) Pageable pageable) {
        var feriados = feriadoService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(feriados);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeriadoResponseDto> updateFeriado(@PathVariable UUID id,
                                                            @RequestBody @Validated FeriadoRequestDto feriadoRequestDto) {
        var existingFeriado = feriadoService.update(id, feriadoRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(existingFeriado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFeriadoById(@PathVariable UUID id) {
        feriadoService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
