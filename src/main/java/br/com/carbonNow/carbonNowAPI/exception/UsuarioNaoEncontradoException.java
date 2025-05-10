package br.com.carbonNow.carbonNowAPI.exception;

public class UsuarioNaoEncontradoException extends RuntimeException {
    public UsuarioNaoEncontradoException(String message) {
        super("Usuario não encontrado: " + message);
    }
}
