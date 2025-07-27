package br.com.bruno.tabelafipe.infrastructure;

public class ApiException extends RuntimeException {

    public ApiException(String message) {
        super(message);
    }
}
