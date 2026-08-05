package api.api_prazo_certo.service.impl;

import api.api_prazo_certo.config.security.SecurityUtils;
import api.api_prazo_certo.dto.request.ProcessoRequestDto;
import api.api_prazo_certo.dto.response.ProcessoResponseDto;
import api.api_prazo_certo.mappers.ProcessoMapper;
import api.api_prazo_certo.model.Cliente;
import api.api_prazo_certo.model.Processo;
import api.api_prazo_certo.model.Usuario;
import api.api_prazo_certo.repository.ClienteRepository;
import api.api_prazo_certo.repository.ProcessoRepository;
import api.api_prazo_certo.service.ProcessoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static api.api_prazo_certo.config.security.SecurityUtils.getUsuarioLogin;

@Service
@RequiredArgsConstructor
public class ProcessoServiceImpl implements ProcessoService {

    private final ProcessoRepository processoRepository;
    private final ClienteRepository clienteRepository;
    private final ProcessoMapper processoMapper;

    @Override
    @Transactional
    public ProcessoResponseDto save(ProcessoRequestDto dto) {
        Usuario usuarioLogin = getUsuarioLogin();

        Cliente cliente = clienteRepository.findByIdAndUsuario(dto.clienteId(), usuarioLogin)
                .orElseThrow(() -> new RuntimeException("O cliente não foi encontrado ou acesso negado."));

        Processo processoEntity = processoMapper.toEntity(dto);
        processoEntity.setUsuario(usuarioLogin);
        processoEntity.setCliente(cliente);

        var processoSave = processoRepository.save(processoEntity);
        return processoMapper.toResponse(processoSave);
    }

    @Override
    public Page<ProcessoResponseDto> findAll(Pageable pageable) {
        return processoRepository.findAllByUsuario(getUsuarioLogin(), pageable)
                .map(processoMapper::toResponse);
    }

    @Override
    public Page<ProcessoResponseDto> findByIdCliente(UUID clienteId, Pageable pageable) {
        return processoRepository.findAllByClienteIdAndUsuario(clienteId, getUsuarioLogin(), pageable)
                .map(processoMapper::toResponse);
    }

    @Override
    @Transactional
    public ProcessoResponseDto update(UUID id, ProcessoRequestDto dto) {
        Usuario usuarioLogin = SecurityUtils.getUsuarioLogin();

        Processo processo = processoRepository.findByIdAndUsuario(id, usuarioLogin)
                .orElseThrow(() -> new RuntimeException("O processo não foi encontrado ou acesso negado."));

        Cliente clienteVinculado = clienteRepository.findByIdAndUsuario(dto.clienteId(), usuarioLogin)
                .orElseThrow(() -> new RuntimeException("O cliente não foi encontrado ou acesso negado."));

        processo.setNumeroProcesso(dto.numeroProcesso());
        processo.setComarca(dto.comarca());
        processo.setVara(dto.vara());
        processo.setInstancia(dto.instancia());
        processo.setPoloCliente(dto.poloCliente());
        processo.setTipoAcao(dto.tipoAcao());
        processo.setValorCausa(dto.valorCausa());
        processo.setLinkTribunal(dto.linkTribunal());
        processo.setCliente(clienteVinculado);

        var processoAtualizado = processoRepository.save(processo);
        return processoMapper.toResponse(processoAtualizado);
    }

    @Override
    public ProcessoResponseDto findById(UUID id) {
        var processo = processoRepository.findByIdAndUsuario(id, SecurityUtils.getUsuarioLogin())
                .orElseThrow(() -> new RuntimeException("O processo não foi encontrado ou acesso negado."));
        return processoMapper.toResponse(processo);
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        var existProcesso = processoRepository.findByIdAndUsuario(id, SecurityUtils.getUsuarioLogin())
                .orElseThrow(() -> new RuntimeException("O processo não foi encontrado ou acesso negado."));
        processoRepository.delete(existProcesso);
    }
}
