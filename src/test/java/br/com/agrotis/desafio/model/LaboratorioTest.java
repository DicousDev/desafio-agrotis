package br.com.agrotis.desafio.model;

import br.com.agrotis.desafio.exception.EntidadeInvalidaRuntimeException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import support.provider.LaboratorioProvider;

import java.util.stream.Stream;

public class LaboratorioTest {

    @ParameterizedTest
    @MethodSource(value = "providerLaboratorioInvalido")
    public void deveValidarErrosBuild(String erro, Laboratorio.LaboratorioBuilder laboratorioBuilder) {
        Assertions.assertThatThrownBy(() -> laboratorioBuilder.build())
                .isInstanceOf(EntidadeInvalidaRuntimeException.class)
                .hasMessage(erro);
    }


    public static Stream<Arguments> providerLaboratorioInvalido() {
        return Stream.of(
            Arguments.of("Nome do laboratório é obrigatório.", LaboratorioProvider.padrao().nome(null)),
            Arguments.of("Nome do laboratório é obrigatório.", LaboratorioProvider.padrao().nome(" ")),
            Arguments.of("O nome do laboratório excedeu o limite de caracteres.", LaboratorioProvider.padrao().nome("a".repeat(256)))
        );
    }
}
