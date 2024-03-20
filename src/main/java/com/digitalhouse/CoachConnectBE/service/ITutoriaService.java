package com.digitalhouse.CoachConnectBE.service;

import com.digitalhouse.CoachConnectBE.entity.Tutoria;

import java.time.LocalDate;
import java.util.List;

public interface ITutoriaService {
    Tutoria guardar(Tutoria tutoria);

    List<Tutoria> listarTodos();

    Tutoria actualizar(Tutoria tutoria);

    void eliminar(Long id);

    List<Tutoria> obtenerTutoriasDisponibles(LocalDate fechaInicio, LocalDate fechaFin);
}
