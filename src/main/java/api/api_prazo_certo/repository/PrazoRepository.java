package api.api_prazo_certo.repository;

import api.api_prazo_certo.model.Prazo;
import api.api_prazo_certo.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PrazoRepository extends JpaRepository<Prazo, UUID> {

    Page<Prazo> findAllByProcessoUsuario(Usuario usuario, Pageable pageable);

    Optional<Prazo> findByIdAndProcessoUsuario(UUID id, Usuario usuario);

    Page<Prazo> findAllByProcessoIdAndProcessoUsuario(UUID processoId, Usuario usuario, Pageable pageable);
}
