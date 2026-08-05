package api.api_prazo_certo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "alerta")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 20)
    private String canal;

    @Column(name = "tipo_notificacao", nullable = false)
    private String tipoNotificacao;

    @Column(nullable = false, length = 20)
    private String status;

    @Column(name = "data_agendamento", nullable = false)
    private LocalDateTime dataAgendamento;

    @Column(name = "data_envio")
    private LocalDateTime dataEnvio;

    @Column(name = "log_mensagem", columnDefinition = "TEXT")
    private String logMensagem;

    @Column(name = "criado_em", updatable = false, nullable = false)
    private LocalDateTime criadoEm;

    @Column(name = "atualizado_em", nullable = false)
    private LocalDateTime atualizadoEm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prazo_id", nullable = false)
    private Prazo prazo;

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
