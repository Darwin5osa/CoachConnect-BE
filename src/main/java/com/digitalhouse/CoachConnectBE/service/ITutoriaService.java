package com.digitalhouse.CoachConnectBE.service;

import com.digitalhouse.CoachConnectBE.entity.Tutoria;

import java.util.List;
import java.util.Set;

public interface ITutoriaService {
    Tutoria guardar(Tutoria tutoria);

    List<Tutoria> listarTodos();

    Tutoria actualizar(Tutoria tutoria, Set<Long> idsCaracteristicas);

    void eliminar(Long id);
}
