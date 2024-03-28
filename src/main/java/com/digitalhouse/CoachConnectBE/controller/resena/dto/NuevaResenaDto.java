package com.digitalhouse.CoachConnectBE.controller.resena.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NuevaResenaDto {
    private Long estudianteId;
    private String contenido;
    private Integer calificacion;
}
