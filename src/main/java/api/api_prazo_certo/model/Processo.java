package api.api_prazo_certo.model;

import api.api_prazo_certo.enums.StatusProcesso;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "processo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Processo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "numero_processo", nullable = false, length = 50)
    private String numeroProcesso;

    @Column(nullable = false, length = 100)
    private String comarca;

    @Column(nullable = false, length = 100)
    private String vara;

    @Column(nullable = false, length = 20)
    private String instancia;

    @Column(name = "polo_cliente", nullable = false, length = 20)
    private String poloCliente;

    @Column(name = "tipo_acao", nullable = false, length = 100)
    private String tipoAcao;

    @Column(name = "valor_causa", nullable = false, precision = 15, scale = 2)
    private BigDecimal valorCausa;

    @Column(name = "link_tribunal", length = 255)
    private String linkTribunal;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private StatusProcesso status;

    @Column(name = "criado_em", updatable = false, nullable = false)
    private LocalDateTime criadoEm;

    @Column(name = "atualizado_em", nullable = false)
    private LocalDateTime atualizadoEm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

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
