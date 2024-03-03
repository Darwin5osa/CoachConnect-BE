package com.digitalhouse.CoachConnectBE.controller.tutoria.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class NuevoTutoriaDto {
    private String nombre;
    private String descripcion;
    private Long nivelId;
    private Long categoriaId;
    private List<Long> caracteristicas;
}
