package api.api_prazo_certo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI configurationOpenAPI() {

        final String securitySchemeName = "bearerAuth";

        return new OpenAPI()
                // informações gerais da API
                .info(new Info()
                        .title("JurisAlerta - API")
                        .version("V1.0.0")
                        .description("API para Gestão de Processos e Cálculo de Prazos Processuais."))

                // aplicando segurança em todas as rotas
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))

                // ensina o Swagger o que é um Bearer Token
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));

    }
}
