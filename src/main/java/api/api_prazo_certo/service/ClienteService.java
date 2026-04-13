package api.api_prazo_certo.service;

import api.api_prazo_certo.dto.request.ClienteRequestDto;
import api.api_prazo_certo.dto.response.ClienteResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ClienteService {

    ClienteResponseDto save(ClienteRequestDto clienteRequestDto);

    Page<ClienteResponseDto> findAll(Pageable pageable);

    ClienteResponseDto update(UUID id, ClienteRequestDto clienteRequestDto);

    ClienteResponseDto findById(UUID id);

    void deleteById(UUID id);
}
