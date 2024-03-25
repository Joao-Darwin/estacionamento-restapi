package com.estacionamento.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotSaveException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public NotSaveException(String msg) {
        super(msg);
    }
}
