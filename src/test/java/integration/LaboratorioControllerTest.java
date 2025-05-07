package integration;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import support.ITemplate;
import support.ResponseEntityAssert;

import java.net.URI;

public class LaboratorioControllerTest extends ITemplate  {

    private static final String BASE_URL = "v1/laboratorios";

    @Test
    @Sql({"classpath:IT/clean.sql"})
    void deveCadastrarLaboratorio() {
        String requestBody = readJSON("IT/cadastro-laboratorio-request.json");
        String expected = readJSON("IT/cadastro-laboratorio-expected.json");
        URI uri = toURI(BASE_URL);
        ResponseEntity<String> response = restTemplate.sendPOST(uri, requestBody);
        ResponseEntityAssert.assertThat(response)
                .isCreated()
                .responseBody(expected);
    }

    @Test
    @Sql({"classpath:IT/clean.sql"})
    void deveFalharNaTentativaDeCadastrarLaboratorioComNomeExcedendoLimite() {
        String requestBody = readJSON("IT/cadastro-laboratorio-nome-limit-request.json");
        String expected = readJSON("IT/cadastro-laboratorio-nome-limit-expected.json");
        URI uri = toURI(BASE_URL);
        ResponseEntity<String> response = restTemplate.sendPOST(uri, requestBody);
        ResponseEntityAssert.assertThat(response)
                .isBadRequest()
                .responseBody(expected, "timestamp");
    }

    @Test
    @Sql({"classpath:IT/clean.sql"})
    void deveFalharNaTentativaDeCadastrarLaboratorioSemNome() {
        String requestBody = readJSON("IT/cadastro-laboratorio-sem-nome-request.json");
        String expected = readJSON("IT/cadastro-laboratorio-sem-nome-expected.json");
        URI uri = toURI(BASE_URL);
        ResponseEntity<String> response = restTemplate.sendPOST(uri, requestBody);
        ResponseEntityAssert.assertThat(response)
                .isBadRequest()
                .responseBody(expected, "timestamp");
    }

    @Test
    @Sql({"classpath:IT/clean.sql",
            "classpath:IT/insert-laboratorios.sql",
            "classpath:IT/insert-propriedades.sql",
            "classpath:IT/insert-pessoas.sql"})
    void devePesquisarLaboratorioPeloId() {
        String expected = readJSON("IT/pesquisa-laboratorio-id-expected.json");
        URI uri = toURI(BASE_URL + "/1");
        ResponseEntity<String> response = restTemplate.sendGET(uri);
        ResponseEntityAssert.assertThat(response)
                .isOk()
                .responseBody(expected);
    }

    @Test
    @Sql({"classpath:IT/clean.sql"})
    void deveFalharAoPesquisarLaboratorioPeloId() {
        String expected = readJSON("IT/pesquisa-laboratorio-id-nao-encontrado-expected.json");
        URI uri = toURI(BASE_URL + "/1");
        ResponseEntity<String> response = restTemplate.sendGET(uri);
        ResponseEntityAssert.assertThat(response)
                .isNotFound()
                .responseBody(expected, "timestamp");
    }

    @Test
    @Sql({"classpath:IT/clean.sql",
            "classpath:IT/insert-laboratorios.sql",
            "classpath:IT/insert-propriedades.sql",
            "classpath:IT/insert-pessoas.sql"})
    void devePesquisarListaDeLaboratoriosPorPagina() {
        String expected = readJSON("IT/pesquisa-laboratorios-expected.json");
        URI uri = toURI(BASE_URL + "?size=5");
        ResponseEntity<String> response = restTemplate.sendGET(uri);
        ResponseEntityAssert.assertThat(response)
                .isOk()
                .responseBody(expected);
    }

    @Test
    @Sql({"classpath:IT/clean.sql",
            "classpath:IT/insert-laboratorios.sql",
            "classpath:IT/insert-propriedades.sql",
            "classpath:IT/insert-pessoas.sql"})
    void devePesquisarListaDeLaboratoriosPorPaginaComFiltroPeloNome() {
        String expected = readJSON("IT/pesquisa-laboratorios-filtro-nome-expected.json");
        URI uri = toURI(BASE_URL + "?nome=gen");
        ResponseEntity<String> response = restTemplate.sendGET(uri);
        ResponseEntityAssert.assertThat(response)
                .isOk()
                .responseBody(expected);
    }


}
