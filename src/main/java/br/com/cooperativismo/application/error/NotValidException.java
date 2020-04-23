package br.com.cooperativismo.application.error;

public class NotValidException extends RuntimeException {

    public NotValidException(String message) {
        super(message);
    }

}
