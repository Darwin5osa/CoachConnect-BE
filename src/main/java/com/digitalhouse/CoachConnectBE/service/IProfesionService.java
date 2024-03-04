package com.digitalhouse.CoachConnectBE.service;

import com.digitalhouse.CoachConnectBE.entity.Profesion;

import java.util.List;

public interface IProfesionService {
    Profesion guardar(Profesion profesion);

    List<Profesion> listarTodos();

    Profesion actualizar(Profesion profesion);

    void eliminar(Long id);

    Profesion encontrarUnoPorId(Long profesionId);
}
