package com.digitalhouse.CoachConnectBE.service;

import com.digitalhouse.CoachConnectBE.entity.Resena;

import java.util.List;

public interface IResenaService {
    Resena guardar(Resena resena);

    List<Resena> listarPorIdEstudiante(Long id);

    List<Resena> listarPorIdTutoria(Long id);

    void eliminar(Long id);
}
