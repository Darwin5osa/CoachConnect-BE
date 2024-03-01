package com.digitalhouse.CoachConnectBE.service.exception;

import lombok.Getter;

@Getter
public class RecursoNoEncontradoException extends RuntimeException {
    private final String message;
    private final Throwable exception;
    private static final String NINGUN_ELEMENTO_MODIFICADO = "Ningun elemento modificado";

    public RecursoNoEncontradoException(String message, Throwable exception) {
        super("El valor " + message + " no es valido");
        this.message = NINGUN_ELEMENTO_MODIFICADO + " " + message;
        this.exception = exception;
    }

    public RecursoNoEncontradoException() {
        super("El valor " + NINGUN_ELEMENTO_MODIFICADO + " no es valido");
        this.message = NINGUN_ELEMENTO_MODIFICADO;
        this.exception = new Exception();
    }
}
