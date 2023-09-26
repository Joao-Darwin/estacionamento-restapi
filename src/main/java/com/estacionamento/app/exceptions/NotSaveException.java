package com.estacionamento.app.exceptions;

public class NotSaveException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public NotSaveException(String msg) {
        super(msg);
    }
}
