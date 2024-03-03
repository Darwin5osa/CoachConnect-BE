package com.digitalhouse.CoachConnectBE.service;

import com.digitalhouse.CoachConnectBE.entity.Categoria;

import java.util.List;

public interface ICategoriaService {
    Categoria guardar(Categoria categoria);

    List<Categoria> listarTodos();

    Categoria actualizar(Categoria categoria);

    void eliminar(Long id);

    Categoria encontrarUnoPorId(Long categoriaId);
}
