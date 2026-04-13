package api.api_prazo_certo.controller;

import api.api_prazo_certo.dto.request.ClienteRequestDto;
import api.api_prazo_certo.dto.response.ClienteResponseDto;
import api.api_prazo_certo.service.ClienteService;
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
@RequestMapping("/api-prazo-certo/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteResponseDto> saveCliente(@RequestBody @Validated ClienteRequestDto clienteRequestDto) {
        var saveCliente = clienteService.save(clienteRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveCliente);
    }

    @GetMapping
    public ResponseEntity<Page<ClienteResponseDto>> findAllClientes(@PageableDefault(size = 10) Pageable pageable) {
        var clientes = clienteService.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(clientes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDto> updateCliente(@PathVariable UUID id,
                                                            @RequestBody @Validated ClienteRequestDto clienteRequestDto) {
        var existingCliente = clienteService.update(id, clienteRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(existingCliente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDto> findClienteById(@PathVariable UUID id) {
        var cliente = clienteService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClienteById(@PathVariable UUID id) {
        clienteService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
