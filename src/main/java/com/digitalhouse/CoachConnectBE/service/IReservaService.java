package com.digitalhouse.CoachConnectBE.service;

import com.digitalhouse.CoachConnectBE.entity.Reserva;

import java.util.List;

public interface IReservaService {
    Reserva guardar(Reserva reserva);

    List<Reserva> listarPorIdEstudiante(Long id);

    Reserva actualizar(Reserva reserva);

    void eliminar(Long id);
}
