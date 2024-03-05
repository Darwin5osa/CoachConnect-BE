package com.digitalhouse.CoachConnectBE.controller.usuario.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UsuarioLoginDto {
    private String email;
    private String password;
}
