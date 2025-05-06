package br.com.agrotis.desafio.exception;

public class NaoEncontradoRuntimeException extends RuntimeException {

    public NaoEncontradoRuntimeException(String erro) {
        super(erro);
    }
}
