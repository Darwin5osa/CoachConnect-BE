package com.digitalhouse.CoachConnectBE.controller.tutoria.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;

@Getter
@AllArgsConstructor
public class TutoriaDisponibilidadDto {
    private Long id;
    private String nombre;
    private String descripcion;
    private Long nivelId;
    private Long categoriaId;
    private Long tutorId;
    private List<Long> caracteristicas;
    private List<String> fotos;
    private String politicas;
    private HashMap<Integer, Boolean> dias;
}
