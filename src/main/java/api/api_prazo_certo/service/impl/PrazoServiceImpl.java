package api.api_prazo_certo.service.impl;

import api.api_prazo_certo.config.security.SecurityUtils;
import api.api_prazo_certo.dto.request.PrazoRequestDto;
import api.api_prazo_certo.dto.response.PrazoResponseDto;
import api.api_prazo_certo.mappers.PrazoMapper;
import api.api_prazo_certo.model.Prazo;
import api.api_prazo_certo.model.Processo;
import api.api_prazo_certo.model.Usuario;
import api.api_prazo_certo.repository.FeriadoRepository;
import api.api_prazo_certo.repository.PrazoRepository;
import api.api_prazo_certo.repository.ProcessoRepository;
import api.api_prazo_certo.service.PrazoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class PrazoServiceImpl implements PrazoService {

    private final PrazoRepository prazoRepository;
    private final PrazoMapper prazoMapper;
    private final ProcessoRepository processoRepository;
    private final FeriadoRepository feriadoRepository;


    @Override
    @Transactional
    public PrazoResponseDto save(PrazoRequestDto dto) {
        Usuario usuarioLogin = SecurityUtils.getUsuarioLogin();

        Processo processoVinculado = processoRepository.findByIdAndUsuario(dto.processoId(), usuarioLogin)
                .orElseThrow(() -> new RuntimeException("O processo não foi encontrado ou acesso negado."));

        LocalDate dataVencimentoCalculado = calculoDataVencimento(dto.dataInicio(),
                dto.quantidadeDias(), dto.somenteDiasUteis());

        Prazo prazoEntity = prazoMapper.toEntity(dto);

        prazoEntity.setDataVencimento(dataVencimentoCalculado);
        prazoEntity.setProcesso(processoVinculado);

        var savePrazo = prazoRepository.save(prazoEntity);
        return prazoMapper.toResponse(savePrazo);
    }

    @Override
    public Page<PrazoResponseDto> findAll(Pageable pageable) {
        return prazoRepository.findAllByProcessoUsuario(SecurityUtils.getUsuarioLogin(), pageable)
                .map(prazoMapper::toResponse);
    }

    @Override
    public Page<PrazoResponseDto> findAllByProcesso(UUID processoId, Pageable pageable) {
        return prazoRepository.findAllByProcessoIdAndProcessoUsuario(processoId, SecurityUtils.getUsuarioLogin(), pageable)
                .map(prazoMapper::toResponse);
    }

    @Override
    @Transactional
    public PrazoResponseDto update(UUID id, PrazoRequestDto dto) {
        Usuario usuarioLogin = SecurityUtils.getUsuarioLogin();

        Prazo prazo = prazoRepository.findByIdAndProcessoUsuario(id, usuarioLogin)
                .orElseThrow(() -> new RuntimeException("O prazo não foi encontrado ou acesso negado."));

        Processo processoVinculado = processoRepository.findByIdAndUsuario(dto.processoId(), usuarioLogin)
                .orElseThrow(() -> new RuntimeException("O processo não foi encontrado ou acesso negado."));

        LocalDate dataVencimentoCalculado = calculoDataVencimento(dto.dataInicio(),
                dto.quantidadeDias(), dto.somenteDiasUteis());


        prazo.setDescricao(dto.descricao());
        prazo.setDataVencimento(dataVencimentoCalculado);
        prazo.setPrioridade(dto.prioridade());
        prazo.setSomenteDiasUteis(dto.somenteDiasUteis());
        prazo.setProcesso(processoVinculado);

        var prazoAtualizado = prazoRepository.save(prazo);
        return prazoMapper.toResponse(prazoAtualizado);
    }

    @Override
    public PrazoResponseDto findById(UUID id) {
        Prazo prazo = prazoRepository.findByIdAndProcessoUsuario(id, SecurityUtils.getUsuarioLogin())
                .orElseThrow(() -> new RuntimeException("O prazo não foi encontrado ou acesso negado."));
        return prazoMapper.toResponse(prazo);
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        var existPrazo = prazoRepository.findByIdAndProcessoUsuario(id, SecurityUtils.getUsuarioLogin())
                .orElseThrow(() -> new RuntimeException("O prazo não foi encontrado ou acesso negado."));
        prazoRepository.delete(existPrazo);
    }

    // calculo para os dias uteis da semana e feriados adicionados dentro do BD
    private LocalDate calculoDataVencimento(LocalDate dataInicial, int diasUteis, boolean somenteDiasUteis) {

        if (!somenteDiasUteis) {
            return dataInicial.plusDays(diasUteis);
        }

        LocalDate dataCalculada = dataInicial;
        int diasAdicionados = 0;

        while(diasAdicionados < diasUteis) {
            dataCalculada = dataCalculada.plusDays(1);

            DayOfWeek diaSemana = dataCalculada.getDayOfWeek();
            boolean isFimDeSemana = (diaSemana == DayOfWeek.SATURDAY || diaSemana == DayOfWeek.SUNDAY);

            boolean isFeriado = feriadoRepository.existsByDataFeriado(dataCalculada);

            if (!isFimDeSemana && !isFeriado) {
                diasAdicionados++;
            }
        }
        return dataCalculada;
    }
}
