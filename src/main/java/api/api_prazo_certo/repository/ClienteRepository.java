package api.api_prazo_certo.repository;

import api.api_prazo_certo.model.Cliente;
import api.api_prazo_certo.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

    Page<Cliente> findAllByUsuario(Usuario usuario, Pageable pageable);

    Optional<Cliente> findByIdAndUsuario(UUID id, Usuario usuario);
}
