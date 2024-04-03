package com.digitalhouse.CoachConnectBE.service.exception;

import lombok.Getter;

@Getter
public class UsuarioConEmailYaExisteException extends RuntimeException {
    private final String message;
    private static final String EMAIL_YA_EXISTE = "El email ya existe: ";

    public UsuarioConEmailYaExisteException(String email) {
        super(EMAIL_YA_EXISTE);
        this.message = EMAIL_YA_EXISTE + email;
    }
}
