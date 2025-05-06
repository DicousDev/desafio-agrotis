package br.com.agrotis.desafio.model;

import br.com.agrotis.desafio.exception.EntidadeInvalidaRuntimeException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import support.provider.PropriedadeProvider;

import java.util.stream.Stream;

public class PropriedadeTest {

    @ParameterizedTest
    @MethodSource(value = "providerPropriedadeInvalido")
    protected void deveValidarErrosBuild(String erro, Propriedade.PropriedadeBuilder propriedadeBuilder) {
        Assertions.assertThatThrownBy(() -> propriedadeBuilder.build())
                .isInstanceOf(EntidadeInvalidaRuntimeException.class)
                .hasMessage(erro);
    }


    protected static Stream<Arguments> providerPropriedadeInvalido() {
        return Stream.of(
                Arguments.of("Nome da propriedade é obrigatório.", PropriedadeProvider.padrao().nome(null)),
                Arguments.of("Nome da propriedade é obrigatório.", PropriedadeProvider.padrao().nome(" ")),
                Arguments.of("O nome da propriedade excedeu o limite de caracteres.", PropriedadeProvider.padrao().nome("a".repeat(256)))
        );
    }
}
