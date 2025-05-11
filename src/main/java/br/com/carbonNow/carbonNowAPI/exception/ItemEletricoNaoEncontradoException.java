package br.com.carbonNow.carbonNowAPI.exception;

public class ItemEletricoNaoEncontradoException extends RuntimeException {
    public ItemEletricoNaoEncontradoException(String message) {
        super("Item elétrico não encontrado: " + message);
    }
}
