package com.digitalhouse.CoachConnectBE.service;


import com.digitalhouse.CoachConnectBE.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    Usuario guardar(Usuario usuario);

    List<Usuario> listarTodos();

    Usuario actualizar(Usuario usuario);

    void eliminar(Long id);

    Optional<Usuario> login(String username, String password);
}
