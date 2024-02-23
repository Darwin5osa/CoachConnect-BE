package com.digitalhouse.CoachConnectBE.service.exception;

import lombok.Getter;

@Getter
public class RecursoNoEncontradoException extends RuntimeException {
    private final String message;
    private final Throwable exception;

    public RecursoNoEncontradoException(String message, Throwable exception) {
        super("El valor " + message + " no es valido");
        this.message = message;
        this.exception = exception;
    }
}
