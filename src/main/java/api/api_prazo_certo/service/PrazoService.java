package api.api_prazo_certo.service;

import api.api_prazo_certo.dto.request.PrazoRequestDto;
import api.api_prazo_certo.dto.response.PrazoResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface PrazoService {

    PrazoResponseDto save(PrazoRequestDto dto);

    Page<PrazoResponseDto> findAll(Pageable pageable);

    Page<PrazoResponseDto> findAllByProcesso(UUID processoId, Pageable pageable);

    PrazoResponseDto update(UUID id, PrazoRequestDto dto);

    PrazoResponseDto findById(UUID id);

    void deleteById(UUID id);
}
