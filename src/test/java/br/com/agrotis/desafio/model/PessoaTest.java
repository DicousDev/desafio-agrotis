package br.com.agrotis.desafio.model;

import br.com.agrotis.desafio.exception.EntidadeInvalidaRuntimeException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import support.provider.PessoaProvider;

import java.time.LocalDateTime;
import java.util.stream.Stream;

public class PessoaTest {


    @ParameterizedTest
    @MethodSource(value = "providerPessoaInvalido")
    public void deveValidarErrosBuild(String erro, Pessoa.PessoaBuilder pessoaBuilder) {
        Assertions.assertThatThrownBy(() -> pessoaBuilder.build())
                .isInstanceOf(EntidadeInvalidaRuntimeException.class)
                .hasMessage(erro);
    }


    public static Stream<Arguments> providerPessoaInvalido() {
        return Stream.of(
                Arguments.of("Nome da pessoa é obrigatório.", PessoaProvider.padrao().nome(null)),
                Arguments.of("Nome da pessoa é obrigatório.", PessoaProvider.padrao().nome(" ")),
                Arguments.of("O nome da pessoa excedeu o limite de caracteres.", PessoaProvider.padrao().nome("a".repeat(256))),
                Arguments.of("É obrigatório anexar uma propriedade a pessoa.", PessoaProvider.padrao().propriedade(null)),
                Arguments.of("É obrigatório anexar um laboratório a pessoa.", PessoaProvider.padrao().laboratorio(null)),
                Arguments.of("Data inicial não pode ser nula.", PessoaProvider.padrao().dataInicial(null)),
                Arguments.of("Data final não pode ser nula.", PessoaProvider.padrao().dataFinal(null)),
                Arguments.of("Data inicial não pode ser igual ou superior a data final.", PessoaProvider.padrao().dataInicial(LocalDateTime.MAX).dataFinal(LocalDateTime.MIN))

        );
    }
}
