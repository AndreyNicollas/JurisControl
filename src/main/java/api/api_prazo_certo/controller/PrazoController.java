package api.api_prazo_certo.controller;

import api.api_prazo_certo.dto.request.PrazoRequestDto;
import api.api_prazo_certo.dto.response.PrazoResponseDto;
import api.api_prazo_certo.service.PrazoService;
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
@RequestMapping("/api-prazo-certo/prazos")
@RequiredArgsConstructor
public class PrazoController {

    private final PrazoService prazoService;

    @PostMapping
    public ResponseEntity<PrazoResponseDto> savePrazo(@RequestBody @Validated PrazoRequestDto prazoRequestDto) {
        var savePrazo = prazoService.save(prazoRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savePrazo);
    }

    @GetMapping
    public ResponseEntity<Page<PrazoResponseDto>> findAllPrazos(@PageableDefault(size = 10) Pageable pageable) {
        var prazos = prazoService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(prazos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrazoResponseDto> updatePrazo(@PathVariable UUID id,
                                                              @RequestBody @Validated PrazoRequestDto prazoRequestDto) {
        var existingPrazo = prazoService.update(id, prazoRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(existingPrazo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrazoResponseDto> findPrazoById(@PathVariable UUID id) {
        var prazo = prazoService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(prazo);
    }

    @GetMapping("/processo/{processoId}")
    public ResponseEntity<Page<PrazoResponseDto>> findAllPrazosByProcesso(@PathVariable UUID processoId,
                                                                          @PageableDefault(size = 10) Pageable pageable) {
        var prazos = prazoService.findAllByProcesso(processoId, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(prazos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrazoById(@PathVariable UUID id) {
        prazoService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
