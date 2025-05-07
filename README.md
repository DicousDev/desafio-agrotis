# Agrotis - Desafio técnico

Desenvolvimento iniciado na madrugada de 06/05/2025 próximo das 2h da manhã e finalizado 07/05/2025 por volta das 2h!

## Requisitos

- Java 17
- Git
- Maven 3.6.3
- Docker 20.10+

## Arquitetura

A simples arquitetura em camadas foi decidida e é reajustada com base no aumento da complexidade do projeto ao longo do tempo de desenvolvimento.
No momento, a arquitetura apresenta como as principais camadas: controller, service, repository, model. 
Podemos também, encontrar testes unitários e testes de integração o que é crucial para mantermos a qualidade das entregas.

## Padrões

O projeto segue boas práticas de clean code, single responsability principle (SRP) e aplica os conceitos de domain driven design (DDD).

## Tecnologias

- Spring boot 3.4.5
- Spring Data Jpa
- Lombok
- Flyway migration
- Testcontainers
- PostgreSQL
- Documentação Swagger

## Executando localmente

- clona o repositório

```shell
git clone git@github.com:DicousDev/desafio-wefit.git
```

- Certifique-se que seu Docker esteja aberto
- Abra a pasta raiz do projeto
- Execute o seguinte comando para subir a aplicação completa (API e database)

```shell
docker-compose up -d
```

- O projeto tem documentação swagger, então podemos acessar API acessando pelo navegador http://localhost:8080/swagger-ui/index.html#/