package api.api_prazo_certo.model;

import api.api_prazo_certo.enums.UsuarioRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(nullable = false, unique = true, length = 14)
    private String cpf;

    @Column(name = "numero_oab", nullable = false, length = 20)
    private String numeroOab;

    @Column(name = "uf_oab", nullable = false, length = 2)
    private String ufOab;

    @Column(name = "especialidade_principal", nullable = false, length = 100)
    private String especialidadePrincipal;

    @Column(name = "telefone_celular", nullable = false, length = 20)
    private String telefoneCelular;

    @Column(nullable = false, length = 255)
    private String password;

    @Enumerated(EnumType.STRING)
    private UsuarioRole role;

    @Column(nullable = false, length = 100)
    private String cidade;

    @Column(name = "criado_em", updatable = false, nullable = false)
    private LocalDateTime criadoEm;

    @Column(name = "atualizado_em", nullable = false)
    private LocalDateTime atualizadoEm;

    @PrePersist
    public void prePersist() {
        LocalDateTime localDateTime = LocalDateTime.now();
        this.criadoEm = localDateTime;
        this.atualizadoEm = localDateTime;
    }

    @PreUpdate
    public void preUpdate() {
        this.atualizadoEm = LocalDateTime.now();
    }

    public Usuario(String nome, String email, String cpf, String numeroOab, String ufOab, String especialidadePrincipal, String telefoneCelular, String password, UsuarioRole role, String cidade) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.numeroOab = numeroOab;
        this.ufOab = ufOab;
        this.especialidadePrincipal = especialidadePrincipal;
        this.telefoneCelular = telefoneCelular;
        this.password = password;
        this.role = role;
        this.cidade = cidade;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UsuarioRole.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
