package api.api_prazo_certo.repository;

import api.api_prazo_certo.model.Processo;
import api.api_prazo_certo.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProcessoRepository extends JpaRepository<Processo, UUID> {

    Page<Processo> findAllByUsuario(Usuario usuario, Pageable pageable);

    Page<Processo> findAllByClienteIdAndUsuario(UUID clienteId, Usuario usuario, Pageable pageable);

    Optional<Processo> findByIdAndUsuario(UUID id, Usuario usuario);
}