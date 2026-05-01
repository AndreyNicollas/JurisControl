package api.api_prazo_certo.service.impl;

import api.api_prazo_certo.config.security.SecurityUtils;
import api.api_prazo_certo.dto.response.DashboardResponseDto;
import api.api_prazo_certo.dto.response.PrazoResponseDto;
import api.api_prazo_certo.enums.StatusPrazo;
import api.api_prazo_certo.enums.StatusProcesso;
import api.api_prazo_certo.mappers.PrazoMapper;
import api.api_prazo_certo.model.Usuario;
import api.api_prazo_certo.repository.ClienteRepository;
import api.api_prazo_certo.repository.PrazoRepository;
import api.api_prazo_certo.repository.ProcessoRepository;
import api.api_prazo_certo.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final ClienteRepository clienteRepository;
    private final ProcessoRepository processoRepository;
    private final PrazoRepository prazoRepository;
    private final PrazoMapper prazoMapper;


    @Override
    public DashboardResponseDto obterResumoDashboard() {
        Usuario usuarioLogin = SecurityUtils.getUsuarioLogin();

        LocalDate hoje = LocalDate.now();
        LocalDate seteDias = hoje.plusDays(7);

        long totalClientes = clienteRepository.countByUsuario(usuarioLogin);

        long totalProcessosAtivos = processoRepository.countByUsuarioAndStatus(
                usuarioLogin, StatusProcesso.ATIVO);

        long prazosUrgentes = prazoRepository.countByProcessoUsuarioAndStatusAndDataVencimentoBetween(
                usuarioLogin, StatusPrazo.PENDENTE, hoje, seteDias);

        long prazosVencidos = prazoRepository.countByProcessoUsuarioAndStatusAndDataVencimentoBefore(usuarioLogin,
                StatusPrazo.PENDENTE, hoje);

        var proximosPrazos = prazoRepository.findByProcessoUsuarioAndStatusOrderByDataVencimentoAsc(
                usuarioLogin, StatusPrazo.PENDENTE);

        List<PrazoResponseDto> prazos = proximosPrazos.stream().map(prazoMapper::toResponse).toList();

        return new DashboardResponseDto(totalClientes, totalProcessosAtivos, prazosUrgentes, prazosVencidos, prazos);
    }
}
