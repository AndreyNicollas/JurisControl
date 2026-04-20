package api.api_prazo_certo.service.impl;

import api.api_prazo_certo.dto.request.ClienteRequestDto;
import api.api_prazo_certo.dto.response.ClienteResponseDto;
import api.api_prazo_certo.mappers.ClienteMapper;
import api.api_prazo_certo.model.Cliente;
import api.api_prazo_certo.repository.ClienteRepository;
import api.api_prazo_certo.repository.UsuarioRepository;
import api.api_prazo_certo.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final UsuarioRepository usuarioRepository;
    private final ClienteMapper clienteMapper;

    @Override
    @Transactional
    public ClienteResponseDto save(ClienteRequestDto clienteRequestDto) {
        var usuario = usuarioRepository.findById(clienteRequestDto.usuarioId())
                .orElseThrow(() -> new RuntimeException("O Advogado responsável não foi encontrado."));

        var clienteEntity = clienteMapper.toEntity(clienteRequestDto);
        clienteEntity.setUsuario(usuario);
        var clienteSave = clienteRepository.save(clienteEntity);
        return clienteMapper.toResponse(clienteSave);
    }

    @Override
    public Page<ClienteResponseDto> findAll(Pageable pageable) {
        return clienteRepository.findAll(pageable).map(clienteMapper::toResponse);
    }

    @Override
    @Transactional
    public ClienteResponseDto update(UUID id, ClienteRequestDto clienteRequestDto) {
        var existingCliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("O cliente não foi encontrado."));

        existingCliente.setNome(clienteRequestDto.nome());
        existingCliente.setCpfCnpj(clienteRequestDto.cpfCnpj());
        existingCliente.setTelefone(clienteRequestDto.telefone());
        existingCliente.setEmail(clienteRequestDto.email());

        var clienteAtualizado = clienteRepository.save(existingCliente);
        return clienteMapper.toResponse(clienteAtualizado);
    }

    @Override
    public ClienteResponseDto findById(UUID id) {
        var cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("O cliente não foi encontrado."));
        return clienteMapper.toResponse(cliente);
    }

    @Override
    public void deleteById(UUID id) {
        clienteRepository.deleteById(id);
    }
}
