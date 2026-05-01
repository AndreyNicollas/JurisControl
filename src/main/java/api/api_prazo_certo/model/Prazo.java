package api.api_prazo_certo.model;

import api.api_prazo_certo.enums.PrioridadePrazo;
import api.api_prazo_certo.enums.StatusPrazo;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "prazo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Prazo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 255)
    private String descricao;

    @Column(name = "data_vencimento", nullable = false)
    private LocalDate dataVencimento;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private PrioridadePrazo prioridade;

    @Column(name = "somente_dias_uteis")
    private Boolean somenteDiasUteis;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private StatusPrazo status;

    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "processo_id", nullable = false)
    private Processo processo;

    @PrePersist
    protected void onCreate() {
        this.dataCriacao = LocalDateTime.now();
        if (this.prioridade == null) this.prioridade = PrioridadePrazo.MEDIA;
        if (this.somenteDiasUteis == null) this.somenteDiasUteis = true;
        if (this.status == null) this.status = StatusPrazo.PENDENTE;
    }
}
