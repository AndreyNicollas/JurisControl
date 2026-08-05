package api.api_prazo_certo.model;

import api.api_prazo_certo.enums.PrioridadePrazo;
import api.api_prazo_certo.enums.StatusPrazo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "prazo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prazo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 255)
    private String descricao;

    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;

    @Column(name = "quantidade_dias", nullable = false)
    private Integer quantidadeDias;

    @Column(name = "data_vencimento", nullable = false)
    private LocalDateTime dataVencimento;

    @Column(name = "data_conclusao_real")
    private LocalDateTime dataConclusaoReal;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private PrioridadePrazo prioridade;

    @Column(name = "somente_dias_uteis")
    private Boolean somenteDiasUteis;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private StatusPrazo status;

    @Column(columnDefinition = "TEXT")
    private String observacao;

    @Column(name = "criado_em", updatable = false, nullable = false)
    private LocalDateTime criadoEm;

    @Column(name = "atualizado_em", nullable = false)
    private LocalDateTime atualizadoEm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "processo_id", nullable = false)
    private Processo processo;

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
