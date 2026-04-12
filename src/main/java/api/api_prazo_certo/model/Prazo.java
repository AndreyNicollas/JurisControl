package api.api_prazo_certo.model;

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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Prazo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 255)
    private String descricao;

    @Column(name = "data_vencimento", nullable = false)
    private LocalDate dataVencimento;

    @Column(length = 20)
    private String prioridade;

    @Column(name = "somente_dias_uteis")
    private Boolean somenteDiasUteis;

    @Column(length = 20)
    private String status;

    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "processo_id", nullable = false)
    private Processo processo;

    protected void onCreate() {
        this.dataCriacao = LocalDateTime.now();
        if (this.prioridade == null) this.prioridade = "media";
        if (this.somenteDiasUteis == null) this.somenteDiasUteis = true;
        if (this.status == null) this.status = "pendente";
    }
}
