package api.api_prazo_certo.repository;

import api.api_prazo_certo.model.Feriado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FeriadoRepository extends JpaRepository<Feriado, UUID> {
}
