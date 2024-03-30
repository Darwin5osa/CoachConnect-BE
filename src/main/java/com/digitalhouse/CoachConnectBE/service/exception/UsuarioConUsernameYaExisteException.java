package com.digitalhouse.CoachConnectBE.service.exception;

import lombok.Getter;

@Getter
public class UsuarioConUsernameYaExisteException extends RuntimeException {
    private final String message;
    private static final String USERNAME_EXISTE = "El username ya existe: ";

    public UsuarioConUsernameYaExisteException(String username) {
        super(USERNAME_EXISTE);
        this.message = USERNAME_EXISTE + username;
    }
}
