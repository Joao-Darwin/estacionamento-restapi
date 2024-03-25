package com.estacionamento.app.exceptions.handler;

import com.estacionamento.app.exceptions.CredentialsUserIsNullException;
import com.estacionamento.app.exceptions.NotFoundException;
import com.estacionamento.app.exceptions.NotSaveException;
import com.estacionamento.app.exceptions.ResponseEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

@ControllerAdvice
@RestController
public class CustomizeExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CredentialsUserIsNullException.class)
    public final ResponseEntity<ResponseEntityException> handlerCredentialsUserIsNullException(Exception exception, WebRequest webRequest) {
        ResponseEntityException responseEntityException = new ResponseEntityException(Instant.now(), exception.getMessage(), webRequest.getDescription(false));

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(responseEntityException);
    }

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ResponseEntityException> handlerNotFoundException(Exception exception, WebRequest webRequest) {
        ResponseEntityException responseEntityException = new ResponseEntityException(Instant.now(), exception.getMessage(), webRequest.getDescription(false));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseEntityException);
    }

    @ExceptionHandler(NotSaveException.class)
    public final ResponseEntity<ResponseEntityException> handlerNotSaveException(Exception exception, WebRequest webRequest) {
        ResponseEntityException responseEntityException = new ResponseEntityException(Instant.now(), exception.getMessage(), webRequest.getDescription(false));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseEntityException);
    }

}
