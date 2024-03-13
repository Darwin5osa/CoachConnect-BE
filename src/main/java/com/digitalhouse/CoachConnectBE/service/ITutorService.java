package com.digitalhouse.CoachConnectBE.service;

import com.digitalhouse.CoachConnectBE.entity.Tutor;

import java.util.List;

public interface ITutorService {
    Tutor guardar(Tutor tutor);

    List<Tutor> listarTodos();

    Tutor actualizar(Tutor tutor);

    void eliminar(Long id);

    Tutor encontrarUnoPorId(Long tutorId);
}
