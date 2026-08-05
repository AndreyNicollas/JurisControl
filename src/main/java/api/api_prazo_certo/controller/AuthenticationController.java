package api.api_prazo_certo.controller;

import api.api_prazo_certo.config.security.AuthenticationDto;
import api.api_prazo_certo.config.security.TokenService;
import api.api_prazo_certo.dto.request.UsuarioRequestDto;
import api.api_prazo_certo.dto.response.LoginResponseDto;
import api.api_prazo_certo.enums.UsuarioRole;
import api.api_prazo_certo.model.Usuario;
import api.api_prazo_certo.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/juris-alerta/auth")
@RequiredArgsConstructor
@Tag(name = "Autenticação", description = "Endpoints para registro e login de novos advogados por meio de Token JWT.")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final TokenService tokenService;

    @SecurityRequirements()
    @Operation(summary = "Login de Usuario/Advogado.",
            description = "Autenticação do advogado com e-mail e senha, retornando o Token Bearer para consumo na API.")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDto authenticationDto) {
        var usuarioNamePassword = new UsernamePasswordAuthenticationToken(authenticationDto.email(), authenticationDto.senha());
        var auth = this.authenticationManager.authenticate(usuarioNamePassword);

        var token = tokenService.generateToken((Usuario) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDto(token));
    }

    @SecurityRequirements()
    @Operation(summary = "Registro de novo Usuario/Advogado.",
            description = "Faz o registro de um novo Usuário/Advogado na API.")
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UsuarioRequestDto  usuarioRequestDto) {
        if (this.usuarioRepository.findByEmail(usuarioRequestDto.email()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: E-mail já cadastro no sistema.");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(usuarioRequestDto.password());
        Usuario newUsuario = Usuario.builder()
                .nome(usuarioRequestDto.nome())
                .email(usuarioRequestDto.email())
                .cpf(usuarioRequestDto.cpf())
                .numeroOab(usuarioRequestDto.numeroOab())
                .password(encryptedPassword)
                .cidade(usuarioRequestDto.cidade())
                .role(UsuarioRole.USUARIO)
                .build();
        usuarioRepository.save(newUsuario);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
