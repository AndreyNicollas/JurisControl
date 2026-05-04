package api.api_prazo_certo.controller;

import api.api_prazo_certo.dto.request.PrazoRequestDto;
import api.api_prazo_certo.dto.response.PrazoResponseDto;
import api.api_prazo_certo.service.PrazoService;
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
@RequestMapping("/v1/juris-alerta/prazos")
@RequiredArgsConstructor
@Tag(name = "Prazos", description = "Endpoints para gerenciamento de prazos processuais.")
public class PrazoController {

    private final PrazoService prazoService;

    @Operation(summary = "Cadastrar novo prazo.",
            description = "Cria um novo prazo processual no sistema.")
    @PostMapping
    public ResponseEntity<PrazoResponseDto> savePrazo(@RequestBody @Validated PrazoRequestDto prazoRequestDto) {
        var savePrazo = prazoService.save(prazoRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savePrazo);
    }

    @Operation(summary = "Listar todos os Prazos.",
            description = "Retorna uma lista paginada de todos os prazos cadastrados.")
    @GetMapping
    public ResponseEntity<Page<PrazoResponseDto>> findAllPrazos(@ParameterObject @PageableDefault(size = 10) Pageable pageable) {
        var prazos = prazoService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(prazos);
    }

    @Operation(summary = "Atualizar Prazo.",
            description = "Atualiza os dados de um prazo existente através do seu ID.")
    @PutMapping("/{id}")
    public ResponseEntity<PrazoResponseDto> updatePrazo(@PathVariable UUID id,
                                                              @RequestBody @Validated PrazoRequestDto prazoRequestDto) {
        var existingPrazo = prazoService.update(id, prazoRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(existingPrazo);
    }

    @Operation(summary = "Buscar Prazo por ID.",
            description = "Retorna os detalhes de um prazo específico através do seu ID.")
    @GetMapping("/{id}")
    public ResponseEntity<PrazoResponseDto> findPrazoById(@PathVariable UUID id) {
        var prazo = prazoService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(prazo);
    }

    @Operation(summary = "Listar Prazos por Processo.",
            description = "Retorna uma lista paginada de prazos associados a um processo específico.")
    @GetMapping("/processo/{processoId}")
    public ResponseEntity<Page<PrazoResponseDto>> findAllPrazosByProcesso(@PathVariable UUID processoId,
                                                                          @ParameterObject @PageableDefault(size = 10) Pageable pageable) {
        var prazos = prazoService.findAllByProcesso(processoId, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(prazos);
    }

    @Operation(summary = "Deletar Prazo.",
            description = "Remove um prazo do sistema através do seu ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrazoById(@PathVariable UUID id) {
        prazoService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
