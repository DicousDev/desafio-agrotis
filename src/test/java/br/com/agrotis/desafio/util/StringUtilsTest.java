package br.com.agrotis.desafio.util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class StringUtilsTest {

    @ParameterizedTest
    @CsvSource({"a 1 0,a10", " a ,a", ","})
    protected void retirarTodosEspacosEmBranco(String actual, String expected) {
        Assertions.assertThat(StringUtils.retirarTodosEspacosEmBranco(actual)).isEqualTo(expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    protected void isBlankTrue(String actual) {
        Assertions.assertThat(StringUtils.isBlank(actual)).isTrue();
    }
}
