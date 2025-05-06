package br.com.agrotis.desafio;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(title = "Agrotis API",
				version = "1.0",
				description = "API para gerenciamento de propriedades, laboratórios e pessoas")
)
public class DesafioAgrotisApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioAgrotisApplication.class, args);
	}

}
