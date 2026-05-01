package api.api_prazo_certo.repository;

import api.api_prazo_certo.model.Feriado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface FeriadoRepository extends JpaRepository<Feriado, UUID> {

    boolean existsByDataFeriado(LocalDate dataFeriado);
}
