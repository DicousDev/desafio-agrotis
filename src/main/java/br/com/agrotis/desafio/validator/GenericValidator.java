package br.com.agrotis.desafio.validator;

import br.com.agrotis.desafio.exception.EntidadeInvalidaRuntimeException;
import br.com.agrotis.desafio.util.StringUtils;

import java.util.Objects;
import java.util.function.BooleanSupplier;

public class GenericValidator {

    public static void validation(String mensagemErro, BooleanSupplier validations) {
        if (validations.getAsBoolean()) {
            throw new EntidadeInvalidaRuntimeException(mensagemErro);
        }
    }

    public static void validateNotNull(String mensagemErro, Object value) {
        validation(mensagemErro, () -> Objects.isNull(value));
    }

    public static void validateMaxLength(String value, int maxSize, String mensagemErro) {
        validation(mensagemErro, () -> Objects.nonNull(value) && value.length() > maxSize);
    }

    public static void validateNotBlank(String mensagemErro, String value) {
        validation(mensagemErro, () -> Objects.isNull(value) || StringUtils.isBlank(value));
    }
}
