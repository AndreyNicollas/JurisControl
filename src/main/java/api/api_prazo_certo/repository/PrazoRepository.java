package api.api_prazo_certo.repository;

import api.api_prazo_certo.model.Prazo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PrazoRepository extends JpaRepository<Prazo, UUID> {
}
