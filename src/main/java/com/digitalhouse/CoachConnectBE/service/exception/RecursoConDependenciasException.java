package com.digitalhouse.CoachConnectBE.service.exception;

import lombok.Getter;

@Getter
public class RecursoConDependenciasException extends RuntimeException {
    private final String message;
    private static final String ELEMENTO_CON_DEPENDENCIAS = "El elemento tiene dependencias";

    public RecursoConDependenciasException() {
        super(ELEMENTO_CON_DEPENDENCIAS);
        this.message = ELEMENTO_CON_DEPENDENCIAS;
    }
}
