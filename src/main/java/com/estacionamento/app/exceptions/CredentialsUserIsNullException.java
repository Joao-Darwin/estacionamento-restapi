package com.estacionamento.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class CredentialsUserIsNullException extends RuntimeException{

    public CredentialsUserIsNullException() {
        super("Credentials User is null");
    }
}
