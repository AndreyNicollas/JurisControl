package api.api_prazo_certo.model;

import api.api_prazo_certo.enums.StatusProcesso;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "processo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Processo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "numero_processo", nullable = false, length = 50)
    private String numeroProcesso;

    @Column(nullable = false, length = 100)
    private String comarca;

    @Column(length = 100)
    private String vara;

    @Column(name = "tipo_acao", length = 100)
    private String tipoAcao;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private StatusProcesso status;

    @Column(name = "data_cadastro", updatable = false)
    private LocalDateTime dataCadastro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @PrePersist
    protected void onCreate() {
        this.dataCadastro = LocalDateTime.now();
        if (this.status == null) this.status = StatusProcesso.ATIVO;
    }
}
