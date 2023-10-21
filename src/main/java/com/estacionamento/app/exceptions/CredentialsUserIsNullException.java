package com.estacionamento.app.exceptions;

public class CredentialsUserIsNullException extends RuntimeException{

    public CredentialsUserIsNullException() {
        super("Credentials User is null");
    }
}
