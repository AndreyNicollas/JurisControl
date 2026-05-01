package api.api_prazo_certo.model;

import api.api_prazo_certo.enums.AbrangenciaFeriado;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "feriado")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Feriado {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "data_feriado", nullable = false)
    private LocalDate dataFeriado;

    @Column(nullable = false, length = 150)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private AbrangenciaFeriado abrangencia;
}
