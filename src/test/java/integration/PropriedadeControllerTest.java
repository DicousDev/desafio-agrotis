package integration;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import support.ITemplate;
import support.ResponseEntityAssert;

import java.net.URI;

public class PropriedadeControllerTest extends ITemplate  {

    private static final String BASE_URL = "v1/propriedades";

    @Test
    @Sql({"classpath:IT/clean.sql"})
    void deveCadastrarPropriedade() {
        String requestBody = readJSON("IT/cadastro-propriedade-request.json");
        String expected = readJSON("IT/cadastro-propriedade-expected.json");
        URI uri = toURI(BASE_URL);
        ResponseEntity<String> response = restTemplate.sendPOST(uri, requestBody);
        ResponseEntityAssert.assertThat(response)
                .isCreated()
                .responseBody(expected);
    }

    @Test
    @Sql({"classpath:IT/clean.sql"})
    void deveFalharNaTentativaDeCadastrarPropriedadeComNomeExcedendoLimite() {
        String requestBody = readJSON("IT/cadastro-propriedade-nome-limit-request.json");
        String expected = readJSON("IT/cadastro-propriedade-nome-limit-expected.json");
        URI uri = toURI(BASE_URL);
        ResponseEntity<String> response = restTemplate.sendPOST(uri, requestBody);
        ResponseEntityAssert.assertThat(response)
                .isBadRequest()
                .responseBody(expected, "timestamp");
    }

    @Test
    @Sql({"classpath:IT/clean.sql"})
    void deveFalharNaTentativaDeCadastrarPropriedadeSemNome() {
        String requestBody = readJSON("IT/cadastro-propriedade-sem-nome-request.json");
        String expected = readJSON("IT/cadastro-propriedade-sem-nome-expected.json");
        URI uri = toURI(BASE_URL);
        ResponseEntity<String> response = restTemplate.sendPOST(uri, requestBody);
        ResponseEntityAssert.assertThat(response)
                .isBadRequest()
                .responseBody(expected, "timestamp");
    }
}
