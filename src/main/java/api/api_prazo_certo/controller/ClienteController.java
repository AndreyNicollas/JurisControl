package api.api_prazo_certo.controller;

import api.api_prazo_certo.dto.request.ClienteRequestDto;
import api.api_prazo_certo.dto.response.ClienteResponseDto;
import api.api_prazo_certo.service.ClienteService;
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
@RequestMapping("/v1/juris-alerta/clientes")
@RequiredArgsConstructor
@Tag(name = "Clientes", description = "Endpoints para gerenciamento de Clientes.")
public class ClienteController {

    private final ClienteService clienteService;

    @Operation(summary = "Cadastrar Cliente.",
            description = "Cria um novo cliente vinculado ao advogado autenticado.")
    @PostMapping
    public ResponseEntity<ClienteResponseDto> saveCliente(@RequestBody @Validated ClienteRequestDto clienteRequestDto) {
        var saveCliente = clienteService.save(clienteRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveCliente);
    }

    @Operation(summary = "Listar Clientes.",
            description = "Retorna uma lista de todos os clientes do advogado logado.")
    @GetMapping
    public ResponseEntity<Page<ClienteResponseDto>> findAllClientes(@ParameterObject @PageableDefault(size = 10) Pageable pageable) {
        var clientes = clienteService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(clientes);
    }

    @Operation(summary = "Atualizar dados do Cliente.",
            description = "Atualiza os dados de um cliente existente por meio do seu ID.")
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDto> updateCliente(@PathVariable UUID id,
                                                            @RequestBody @Validated ClienteRequestDto clienteRequestDto) {
        var existingCliente = clienteService.update(id, clienteRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(existingCliente);
    }

    @Operation(summary = "Buscar Cliente por ID.",
            description = "Retorna os dados de um cliente específico por meio do seu ID.")
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDto> findClienteById(@PathVariable UUID id) {
        var cliente = clienteService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }

    @Operation(summary = "Deletar Cliente.",
            description = "Remove um cliente do sistema. (Cuidado: Pode impactar processos vinculados a ele).")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClienteById(@PathVariable UUID id) {
        clienteService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
