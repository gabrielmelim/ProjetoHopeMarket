package org.generation.app_hopemarket.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
	
	 	@Bean
		public OpenAPI springOpenAPI() {
			return new OpenAPI()
					.info(new Info().title("Projeto Hope Market")
							.description("Projeto E-commerce")
							.version("v0.0.1")
							.contact(new Contact()
							.name("Repositório Projeto Hope Market")
							.url("https://github.com/gabrielmelim/ProjetoHopeMarket")
							.email("")))
							.externalDocs(new ExternalDocumentation()
							.description("GitHub Integrantes")
							.url("https://linktr.ee/gabrielmelim121325"));
		}
	}
