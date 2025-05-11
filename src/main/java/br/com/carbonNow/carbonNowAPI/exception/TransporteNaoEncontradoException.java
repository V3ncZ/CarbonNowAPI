package br.com.carbonNow.carbonNowAPI.exception;

public class TransporteNaoEncontradoException extends RuntimeException {
    public TransporteNaoEncontradoException(String message) {
        super("Transporte não encontrado: " + message);
    }
}
