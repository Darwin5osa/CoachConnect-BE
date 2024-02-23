package com.digitalhouse.CoachConnectBE.service;


import com.digitalhouse.CoachConnectBE.entity.Usuario;

import java.util.List;

public interface IUsuarioService {
    Usuario guardar(Usuario usuario);

    List<Usuario> listarTodos();

    Usuario actualizar(Usuario usuario);

    void eliminar(Long id);
}
