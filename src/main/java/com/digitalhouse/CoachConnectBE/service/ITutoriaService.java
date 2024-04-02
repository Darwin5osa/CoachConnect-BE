package com.digitalhouse.CoachConnectBE.service;

import com.digitalhouse.CoachConnectBE.entity.DiaReservado;
import com.digitalhouse.CoachConnectBE.entity.Tutoria;
import org.antlr.v4.runtime.misc.Pair;

import java.time.LocalDate;
import java.util.List;

public interface ITutoriaService {
    Tutoria guardar(Tutoria tutoria);

    List<Tutoria> listarTodos();

    Tutoria actualizar(Tutoria tutoria);

    void eliminar(Long id);

    List<Tutoria> obtenerTutoriasDisponibles(LocalDate fechaInicio, LocalDate fechaFin);

    Pair<Tutoria, List<DiaReservado>> obtenerTutoriaConDisponibilidad(Long id);

    Tutoria obtenerTutoria(Long id);
}
