# ⚖️ JurisControl - API Backend

![Java](https://img.shields.io/badge/Java-17+-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=Swagger&logoColor=black)

Back-end desenvolvido para o sistema inteligente **JurisControl**, voltado para a gestão de processos judiciais e cálculo automatizado de prazos para advogados, 
focado em arquitetura limpa, segurança e regras de negócio.

## Visão Geral

O JurisControl é a API responsável por garantir a segurança dos dados e o processamento matemático dos prazos processuais. Suas principais funcionalidades incluem:

*   **Segurança:** Autenticação via Token JWT. Cada advogado possui isolamento total de seus dados (clientes, processos e prazos).
*   **Gestão de Clientes e Processos:** Cadastro completo de clientes e acompanhamento de processos judiciais.
*   **Motor Inteligente de Prazos:** Cálculo automático de vencimentos de prazos, considerando a contagem em dias úteis ou corridos.
*   **Gestão de Feriados:** Tabela de feriados e suspensões de expedientes que alimentam dinamicamente o cálculo de dias úteis.
*   **Dashboard Analítico:** Consultas otimizadas para geração de estatísticas em tempo real (totalizadores de processos, cliente, prazos e vencimentos).
*   **Documentação Viva:** API 100% documentada via Springdoc OpenAPI (Swagger).

## Tecnologias Utilizadas

*   **Linguagem:** Java 21
*   **Framework:** Spring Boot 3
*   **Segurança:** Spring Security + JWT
*   **Persistência:** Spring Data JPA + Hibernate
*   **Banco de Dados:** PostgreSQL
*   **Documentação:** Springdoc OpenAPI (Swagger UI)
*   **Utilitários:** Lombok, Spring Boot Validation

## Estrutura do Projeto

O projeto segue o padrão de implementação em camadas para garantir o princípio de responsabilidade única e facilitar a manutenção:

```text
api-prazo-certo/
├── src/main/java/api/api_prazo_certo/
│   ├── config/         # Configurações do Spring (Security, Cors, Swagger)
│   ├── controller/     # Endpoints da API REST (Ponto de entrada)
│   ├── dto/            # Objetos de Transferência de Dados (Request/Response)
│   ├── enums/          # Tipos enumerados (Status de Prazos, Roles)
│   ├── model/          # Entidades de mapeamento objeto-relacional (JPA)
│   ├── repository/     # Interfaces de acesso ao banco de dados (Spring Data)
│   └── service/        # Camada de regras de negócio e cálculos
└── pom.xml             # Gerenciador de dependências Maven
```
## Como Clonar:

Pré-requisitos → Java 21, Maven, Postman e PostgreSQL.

```text
git clone https://github.com/AndreyNicollas/api-prazo-certo.git
```

## Documentação da API (Swagger UI):

Com a aplicação rodando, acesse no seu navegador:

- http://localhost:8080/swagger-ui/index.html

## Autores

O projeto JurisControl é um projeto feito pela equipe de alunos de Análise e Desenvolvimento de Sistemas da Universidade Estadual do Maranhão (UEMA):

- **Andrey Nicollas**\
Autor principal e mantedor.\
https://github.com/AndreyNicollas

- **Afonso Gabriel**\
Co-autor e colaborador no desenvolvimento.\
https://github.com/1colossos

- **Adrian Raul**\
Co-autor e colaborador no desenvolvimento.\
https://github.com/adrianRaulDev



