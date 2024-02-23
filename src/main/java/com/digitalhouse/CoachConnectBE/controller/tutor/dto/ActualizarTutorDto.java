package com.digitalhouse.CoachConnectBE.controller.tutor.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ActualizarTutorDto {
    private String nombre;
    private String apellido;
    private int edad;
    private String email;
    private String contactoCelular;
    private String foto;
    private String profesion;

    private String descripcion;
}
