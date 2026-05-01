package api.api_prazo_certo.repository;

import api.api_prazo_certo.model.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AlertaRepository extends JpaRepository<Alerta, UUID> {
}
