package com.digitalhouse.CoachConnectBE.service;

import com.digitalhouse.CoachConnectBE.entity.Caracteristica;

import java.util.List;

public interface ICaracteristicaService {
    Caracteristica guardar(Caracteristica caracteristica);

    List<Caracteristica> listarTodos();

    Caracteristica actualizar(Caracteristica caracteristica);

    void eliminar(Long id);
}
