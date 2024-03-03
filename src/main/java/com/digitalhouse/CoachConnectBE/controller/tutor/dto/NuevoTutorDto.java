package com.digitalhouse.CoachConnectBE.controller.tutor.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NuevoTutorDto {
    private String nombre;
    private String apellido;
    private int edad;
    private String email;
    private String contactoCelular;
    private String foto;
    private String username;
    private Long profesion;

    private String descripcion;
}
