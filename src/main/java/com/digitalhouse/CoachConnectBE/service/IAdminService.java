package com.digitalhouse.CoachConnectBE.service;

import com.digitalhouse.CoachConnectBE.entity.Admin;

import java.util.List;

public interface IAdminService {
    Admin guardar(Admin admin);

    List<Admin> listarTodos();

    Admin actualizar(Admin admin);

    void eliminar(Long id);
}
