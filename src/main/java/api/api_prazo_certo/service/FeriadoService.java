package api.api_prazo_certo.service;

import api.api_prazo_certo.dto.request.FeriadoRequestDto;
import api.api_prazo_certo.dto.response.FeriadoResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface FeriadoService {

    FeriadoResponseDto save(FeriadoRequestDto feriadoRequestDto);

    Page<FeriadoResponseDto> findAll(Pageable pageable);

    FeriadoResponseDto update(UUID id, FeriadoRequestDto feriadoRequestDto);

    void deleteById(UUID id);
}
