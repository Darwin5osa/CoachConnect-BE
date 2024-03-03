package com.digitalhouse.CoachConnectBE.service;

import com.digitalhouse.CoachConnectBE.entity.Nivel;

import java.util.List;

public interface INivelService {
    Nivel guardar(Nivel nivel);

    List<Nivel> listarTodos();

    Nivel actualizar(Nivel nivel);

    void eliminar(Long id);

    Nivel encontrarUnoPorId(Long nivelId);
}
