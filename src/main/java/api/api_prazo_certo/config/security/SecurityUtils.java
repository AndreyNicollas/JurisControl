package api.api_prazo_certo.config.security;

import api.api_prazo_certo.model.Usuario;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SecurityUtils {

    public static Usuario getUsuarioLogin() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        return (Usuario) authentication.getPrincipal();
    }
}
