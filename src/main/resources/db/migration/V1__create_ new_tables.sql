-- 1. Criação da tabela USUARIO (Advogado)
CREATE TABLE usuario (
                         id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
                         nome VARCHAR(150) NOT NULL,
                         email VARCHAR(150) UNIQUE NOT NULL,
                         cpf VARCHAR(14) UNIQUE NOT NULL,
                         numero_oab VARCHAR(20) NOT NULL,
                         uf_oab VARCHAR(2) NOT NULL,
                         especialidade_principal VARCHAR(100) NOT NULL,
                         telefone_celular VARCHAR(20) NOT NULL,
                         password VARCHAR(255) NOT NULL,
                         role VARCHAR(20) NOT NULL DEFAULT 'USUARIO',
                         cidade VARCHAR(100) NOT NULL,
                         criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         atualizado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. Criação da tabela CLIENTE
CREATE TABLE cliente (
                         id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
                         usuario_id UUID NOT NULL,
                         nome VARCHAR(150) NOT NULL,
                         cpf_cnpj VARCHAR(18) UNIQUE NOT NULL,
                         tipo_pessoa VARCHAR(10) NOT NULL, -- FÍSICA ou JURÍDICA
                         estado_civil VARCHAR(50) NOT NULL,
                         profissao VARCHAR(50) NOT NULL,
                         telefone VARCHAR(20) NOT NULL,
                         email VARCHAR(150) NOT NULL,
                         cep VARCHAR(10) NOT NULL,
                         logradouro VARCHAR(150),
                         numero VARCHAR(20) NOT NULL,
                         bairro VARCHAR(100) NOT NULL,
                         cidade VARCHAR(100) NOT NULL,
                         uf VARCHAR(2) NOT NULL,
                         criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         atualizado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         CONSTRAINT fk_cliente_usuario FOREIGN KEY (usuario_id) REFERENCES usuario (id) ON DELETE CASCADE,
                         CONSTRAINT uk_usuario_cpfcnpj UNIQUE (usuario_id, cpf_cnpj) -- Impede que o mesmo advogado cadastre o mesmo cliente 2x
);

-- 3. Criação da tabela PROCESSO
CREATE TABLE processo (
                          id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
                          usuario_id UUID NOT NULL,
                          cliente_id UUID NOT NULL,
                          numero_processo VARCHAR(50) NOT NULL,
                          comarca VARCHAR(100) NOT NULL,
                          vara VARCHAR(100) NOT NULL,
                          instancia VARCHAR(20) NOT NULL, -- 1ª Instância, 2ª Instância, etc.
                          polo_cliente VARCHAR(20) NOT NULL, -- Ativo (Autor) ou Passivo (Réu)
                          tipo_acao VARCHAR(100) NOT NULL,
                          valor_causa DECIMAL(15,2) NOT NULL,
                          link_tribunal VARCHAR(255),
                          status VARCHAR(20) DEFAULT 'ATIVO',
                          criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          atualizado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          CONSTRAINT fk_processo_usuario FOREIGN KEY (usuario_id) REFERENCES usuario (id) ON DELETE CASCADE,
                          CONSTRAINT fk_processo_cliente FOREIGN KEY (cliente_id) REFERENCES cliente (id) ON DELETE RESTRICT
);

-- 4. Criação da tabela PRAZO
CREATE TABLE prazo (
                       id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
                       processo_id UUID NOT NULL,
                       descricao VARCHAR(255) NOT NULL,
                       data_inicio DATE NOT NULL, -- Essencial para o recálculo
                       quantidade_dias INT NOT NULL,
                       data_vencimento DATE NOT NULL,
                       data_conclusao_real TIMESTAMP, -- Quando o advogado realmente cumpriu
                       prioridade VARCHAR(20) DEFAULT 'MEDIA',
                       somente_dias_uteis BOOLEAN DEFAULT TRUE,
                       status VARCHAR(20) DEFAULT 'PENDENTE',
                       observacao TEXT,
                       criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       atualizado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       CONSTRAINT fk_prazo_processo FOREIGN KEY (processo_id) REFERENCES processo (id) ON DELETE CASCADE
);

-- 5. Criação da tabela FERIADO
CREATE TABLE feriado (
                         id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
                         data_feriado DATE NOT NULL,
                         descricao VARCHAR(150) NOT NULL,
                         abrangencia VARCHAR(30) NOT NULL, -- NACIONAL, ESTADUAL, MUNICIPAL
                         uf VARCHAR(2) NOT NULL, -- Usado se for Estadual/Municipal
                         municipio VARCHAR(100) NOT NULL, -- Usado se for Municipal
                         criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         atualizado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 6. Criação da tabela ALERTA
CREATE TABLE alerta (
                        id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
                        prazo_id UUID NOT NULL,
                        canal VARCHAR(20) NOT NULL, -- WHATSAPP, EMAIL, SISTEMA
                        tipo_notificacao VARCHAR(50), -- VENCIMENTO_PROXIMO, VENCIDO, CRIADO
                        status VARCHAR(20) NOT NULL DEFAULT 'AGENDADO',
                        data_agendamento TIMESTAMP NOT NULL,
                        data_envio TIMESTAMP,
                        log_mensagem TEXT,
                        criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        atualizado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                        CONSTRAINT fk_alerta_prazo FOREIGN KEY (prazo_id) REFERENCES prazo (id) ON DELETE CASCADE
);