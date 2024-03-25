package com.estacionamento.app.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

@Getter
@Setter
public class ResponseEntityException implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

    private Instant moment;
    private String message;
    private String details;

    public ResponseEntityException(Instant moment, String message, String details) {
        this.moment = Instant.parse(simpleDateFormat.format(Date.from(moment)));
        this.message = message;
        this.details = details;
    }
}
