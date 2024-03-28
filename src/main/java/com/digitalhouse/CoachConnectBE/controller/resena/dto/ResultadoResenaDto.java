package com.digitalhouse.CoachConnectBE.controller.resena.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResultadoResenaDto {
    private Long estudianteId;
    private Long tutoriaId;
    private String contenido;
    private Integer calificacion;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime fechaPublicacion;
}
