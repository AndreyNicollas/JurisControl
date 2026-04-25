package api.api_prazo_certo.service;

import api.api_prazo_certo.dto.request.ProcessoRequestDto;
import api.api_prazo_certo.dto.response.ProcessoResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ProcessoService {

    ProcessoResponseDto save(ProcessoRequestDto dto);

    Page<ProcessoResponseDto> findAll(Pageable pageable);

    Page<ProcessoResponseDto> findByIdCliente(UUID clienteId, Pageable pageable);

    ProcessoResponseDto update(UUID id, ProcessoRequestDto dto);

    ProcessoResponseDto findById(UUID id);

    void deleteById(UUID id);
}
