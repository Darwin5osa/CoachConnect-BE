package com.digitalhouse.CoachConnectBE.controller.tutoria.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class TutoriaResultadoDto {
    private Long id;
    private String nombre;
    private String descripcion;
    private Long nivelId;
    private Long categoriaId;
    private List<Long> caracteristicas;
}
