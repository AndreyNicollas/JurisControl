package api.api_prazo_certo.service.impl;

import api.api_prazo_certo.dto.request.FeriadoRequestDto;
import api.api_prazo_certo.dto.response.FeriadoResponseDto;
import api.api_prazo_certo.mappers.FeriadoMapper;
import api.api_prazo_certo.repository.FeriadoRepository;
import api.api_prazo_certo.service.FeriadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FeriadoServiceImpl implements FeriadoService {

    private final FeriadoRepository feriadoRepository;
    private final FeriadoMapper feriadoMapper;

    @Override
    @Transactional
    public FeriadoResponseDto save(FeriadoRequestDto feriadoRequestDto) {
        var feriado = feriadoMapper.toEntity(feriadoRequestDto);
        return feriadoMapper.toResponse(feriadoRepository.save(feriado));
    }

    @Override
    public Page<FeriadoResponseDto> findAll(Pageable pageable) {
        return feriadoRepository.findAll(pageable).map(feriadoMapper::toResponse);
    }

    @Override
    @Transactional
    public FeriadoResponseDto update(UUID id, FeriadoRequestDto feriadoRequestDto) {
        var existingFeriado = feriadoRepository
                .findById(id).orElseThrow(() -> new RuntimeException("Nenhum feriado foi encontrado."));

        existingFeriado.setDataFeriado(feriadoRequestDto.dataFeriado());
        existingFeriado.setDescricao(feriadoRequestDto.descricao());
        existingFeriado.setAbrangencia(feriadoRequestDto.abrangencia());

        return feriadoMapper.toResponse(feriadoRepository.save(existingFeriado));
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        if (!feriadoRepository.existsById(id)) {
            throw new RuntimeException("Nenhum feriado foi encontrado.");
        }
        feriadoRepository.deleteById(id);
    }
}
