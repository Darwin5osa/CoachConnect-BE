package com.digitalhouse.CoachConnectBE.controller.usuario.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CambioEstadoDto {
    private String username;
    private Boolean habilitado;
}
