package api.api_prazo_certo.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ClienteResponseDto(
        UUID id,
        String nome,
        String cpfCnpj,
        String tipoPessoa,
        String estadoCivil,
        String profissao,
        String telefone,
        String email,
        String cep,
        String logradouro,
        String numero,
        String bairro,
        String cidade,
        String uf,
        LocalDateTime criadoEm,
        LocalDateTime atualizadoEm,

        //@JsonIgnoreProperties({"cidade", "role"})
        UsuarioResponseDto usuario
) {}
