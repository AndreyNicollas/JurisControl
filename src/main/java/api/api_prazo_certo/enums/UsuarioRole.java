package api.api_prazo_certo.enums;

import lombok.Getter;

@Getter
public enum UsuarioRole {
    ADMIN("ROLE_ADMIN"),
    USUARIO("ROLE_USER");

    private String role;

    UsuarioRole(String role) {
        this.role = role;
    }
}
