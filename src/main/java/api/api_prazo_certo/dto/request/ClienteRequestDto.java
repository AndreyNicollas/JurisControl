package api.api_prazo_certo.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ClienteRequestDto(
        @NotBlank(message = "O campo nome é obrigatório.")
        String nome,

        @NotBlank(message = "O campo CPF ou CNPJ é obrigatório.")
        String cpfCnpj,

        @NotBlank(message = "O campo tipo pessoa é obrigatório.")
        String tipoPessoa,

        @NotBlank(message = "O campo estado civil é obrigatório.")
        String estadoCivil,

        @NotBlank(message = "O campo profissão é obrigatório.")
        String profissao,

        @NotBlank(message = "O campo telefone é obrigatório.")
        String telefone,

        @NotBlank(message = "O campo email é obrigatório.")
        String email,

        @NotBlank(message = "O campo CEP é obrigatório.")
        String cep,

        String logradouro,

        @NotBlank(message = "O campo número é obrigatório.")
        String numero,

        @NotBlank(message = "O campo bairro é obrigatório.")
        String bairro,

        @NotBlank(message = "O campo cidade é obrigatório.")
        String cidade,

        @NotBlank(message = "O campo UF é obrigatório.")
        String uf

//        @NotNull(message = "O campo usuário é obrigatório.")
//        UUID usuarioId
) {}
