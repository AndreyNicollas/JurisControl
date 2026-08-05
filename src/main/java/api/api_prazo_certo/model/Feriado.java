package api.api_prazo_certo.model;

import api.api_prazo_certo.enums.AbrangenciaFeriado;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "feriado")
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(nullable = false, length = 2)
    private String uf;

    @Column(nullable = false, length = 100)
    private String municipio;

    @Column(name = "criado_em", updatable = false, nullable = false)
    private LocalDateTime criadoEm;

    @Column(name = "atualizado_em", nullable = false)
    private LocalDateTime atualizadoEm;

    @PrePersist
    protected void prePersist() {
        LocalDateTime localDateTime = LocalDateTime.now();
        this.criadoEm = localDateTime;
        this.atualizadoEm = localDateTime;
    }

    @PreUpdate
    public void preUpdate() {
        this.atualizadoEm = LocalDateTime.now();
    }

}
