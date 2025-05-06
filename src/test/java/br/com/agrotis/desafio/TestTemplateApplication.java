package br.com.agrotis.desafio;

import org.springframework.boot.SpringApplication;

public class TestTemplateApplication {

	public static void main(String[] args) {
		SpringApplication.from(DesafioAgrotisApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
