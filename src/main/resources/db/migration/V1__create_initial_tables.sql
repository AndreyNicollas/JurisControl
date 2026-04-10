-- 1. Criação da tabela USUARIO (Advogado)
CREATE TABLE usuario (
                         id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
                         nome VARCHAR(150) NOT NULL,
                         email VARCHAR(150) UNIQUE NOT NULL,
                         cpf VARCHAR(14) UNIQUE,
                         numero_oab VARCHAR(20) NOT NULL,
                         senha_hash VARCHAR(255) NOT NULL,
                         cidade VARCHAR(100),
                         data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. Criação da tabela CLIENTE
CREATE TABLE cliente (
                         id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
                         usuario_id UUID NOT NULL,
                         nome VARCHAR(150) NOT NULL,
                         cpf_cnpj VARCHAR(18) NOT NULL,
                         telefone VARCHAR(20),
                         email VARCHAR(150),
                         data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         CONSTRAINT fk_cliente_usuario FOREIGN KEY (usuario_id) REFERENCES usuario (id) ON DELETE CASCADE
);

-- 3. Criação da tabela PROCESSO
CREATE TABLE processo (
                          id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
                          usuario_id UUID NOT NULL,
                          cliente_id UUID NOT NULL,
                          numero_processo VARCHAR(50) NOT NULL,
                          comarca VARCHAR(100) NOT NULL,
                          vara VARCHAR(100),
                          tipo_acao VARCHAR(100),
                          status VARCHAR(20) DEFAULT 'ativo',
                          data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          CONSTRAINT fk_processo_usuario FOREIGN KEY (usuario_id) REFERENCES usuario (id) ON DELETE CASCADE,
                          CONSTRAINT fk_processo_cliente FOREIGN KEY (cliente_id) REFERENCES cliente (id) ON DELETE RESTRICT
);

-- 4. Criação da tabela PRAZO
CREATE TABLE prazo (
                       id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
                       processo_id UUID NOT NULL,
                       descricao VARCHAR(255) NOT NULL,
                       data_vencimento DATE NOT NULL,
                       prioridade VARCHAR(20) DEFAULT 'media',
                       somente_dias_uteis BOOLEAN DEFAULT TRUE,
                       status VARCHAR(20) DEFAULT 'pendente',
                       data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       CONSTRAINT fk_prazo_processo FOREIGN KEY (processo_id) REFERENCES processo (id) ON DELETE CASCADE
);

-- 5. Criação da tabela FERIADO
CREATE TABLE feriado (
                         id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
                         data_feriado DATE NOT NULL,
                         descricao VARCHAR(150) NOT NULL,
                         abrangencia VARCHAR(30) NOT NULL
);

-- 6. Criação da tabela ALERTA
CREATE TABLE alerta (
                        id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
                        prazo_id UUID NOT NULL,
                        canal VARCHAR(20) NOT NULL,
                        status VARCHAR(20) NOT NULL,
                        data_agendamento TIMESTAMP NOT NULL,
                        data_envio TIMESTAMP,
                        log_mensagem TEXT,
                        CONSTRAINT fk_alerta_prazo FOREIGN KEY (prazo_id) REFERENCES prazo (id) ON DELETE CASCADE
);