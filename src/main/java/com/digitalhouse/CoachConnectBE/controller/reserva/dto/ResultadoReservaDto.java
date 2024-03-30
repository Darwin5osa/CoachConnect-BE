package com.digitalhouse.CoachConnectBE.controller.reserva.dto;

import com.digitalhouse.CoachConnectBE.controller.estudiante.dto.EstudianteResultadoDto;
import com.digitalhouse.CoachConnectBE.controller.tutoria.dto.TutoriaResultadoDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResultadoReservaDto {
    private Long id;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fechaInicio;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fechaFin;
    private int horasReservadas;
    private EstudianteResultadoDto estudiante;
    private TutoriaResultadoDto tutoria;
}
