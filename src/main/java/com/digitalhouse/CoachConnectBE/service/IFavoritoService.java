package com.digitalhouse.CoachConnectBE.service;

import com.digitalhouse.CoachConnectBE.entity.Favorito;
import com.digitalhouse.CoachConnectBE.entity.Tutoria;

import java.util.List;

public interface IFavoritoService {
    void guardar(Favorito favorito);

    List<Tutoria> listarPorIdEstudiante(Long id);

    void eliminar(Long id);
}
