package integration;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import support.ITemplate;
import support.ResponseEntityAssert;

import java.net.URI;

public class PessoaControllerTest extends ITemplate {

    private static final String BASE_URL = "v1/pessoas";

    @Test
    @Sql({"classpath:IT/clean.sql", "classpath:IT/insert-laboratorios.sql", "classpath:IT/insert-propriedades.sql"})
    void deveCadastrarPessoa() {
        String requestBody = readJSON("IT/cadastro-pessoa-request.json");
        String expected = readJSON("IT/cadastro-pessoa-expected.json");
        URI uri = toURI(BASE_URL);
        ResponseEntity<String> response = restTemplate.sendPOST(uri, requestBody);
        ResponseEntityAssert.assertThat(response)
                .isCreated()
                .responseBody(expected);
    }

    @Test
    @Sql({"classpath:IT/clean.sql"})
    void deveFalharAoCadastrarPessoaQuandoNaoEncontrarPropriedade() {
        String requestBody = readJSON("IT/cadastro-pessoa-propriedade-nao-encontrado-request.json");
        String expected = readJSON("IT/cadastro-pessoa-propriedade-nao-encontrado-expected.json");
        URI uri = toURI(BASE_URL);
        ResponseEntity<String> response = restTemplate.sendPOST(uri, requestBody);
        ResponseEntityAssert.assertThat(response)
                .isNotFound()
                .responseBody(expected, "timestamp");
    }

    @Test
    @Sql({"classpath:IT/clean.sql"})
    void deveFalharAoCadastrarPessoaQuandoNaoEncontrarLaboratorio() {
        String requestBody = readJSON("IT/cadastro-pessoa-laboratorio-nao-encontrado-request.json");
        String expected = readJSON("IT/cadastro-pessoa-laboratorio-nao-encontrado-expected.json");
        URI uri = toURI(BASE_URL);
        ResponseEntity<String> response = restTemplate.sendPOST(uri, requestBody);
        ResponseEntityAssert.assertThat(response)
                .isNotFound()
                .responseBody(expected, "timestamp");
    }

    @Test
    @Sql({"classpath:IT/clean.sql"})
    void deveFalharAoEnviarDadosInvalidos() {
        String requestBody = readJSON("IT/cadastro-pessoa-payload-invalido-request.json");
        String expected = readJSON("IT/cadastro-pessoa-payload-invalido-expected.json");
        URI uri = toURI(BASE_URL);
        ResponseEntity<String> response = restTemplate.sendPOST(uri, requestBody);
        ResponseEntityAssert.assertThat(response)
                .isBadRequest()
                .responseBody(expected, "timestamp");
    }


    @Test
    @Sql({"classpath:IT/clean.sql", "classpath:IT/insert-laboratorios.sql",
            "classpath:IT/insert-propriedades.sql",
            "classpath:IT/insert-pessoas.sql"})
    void deveConsultarPessoas() {
        String expected = readJSON("IT/consulta-pessoas-expected.json");
        URI uri = toURI(BASE_URL);
        ResponseEntity<String> response = restTemplate.sendGET(uri);
        ResponseEntityAssert.assertThat(response)
                .isOk()
                .responseBody(expected);
    }
}
