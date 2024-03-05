package com.digitalhouse.CoachConnectBE.service.impl;


import com.digitalhouse.CoachConnectBE.entity.Admin;
import com.digitalhouse.CoachConnectBE.repository.AdminRepository;
import com.digitalhouse.CoachConnectBE.service.IAdminService;
import com.digitalhouse.CoachConnectBE.service.IUsuarioService;
import com.digitalhouse.CoachConnectBE.service.exception.RecursoNoEncontradoException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AdminService implements IAdminService {

    private final AdminRepository adminReository;
    private final IUsuarioService usuarioService;

    public Admin guardar(Admin admin) {
        admin.setUsuario(usuarioService.guardar(admin.getUsuario()));
        admin = adminReository.save(admin);
        log.debug("Se guardo el admin id " + admin.getId());
        return admin;
    }

    public List<Admin> listarTodos() {
        try {
            return adminReository.findAll();
        } catch (NoSuchElementException | EntityNotFoundException exception) {
            throw new RecursoNoEncontradoException(exception.getMessage(), exception);
        }
    }

    @Override
    public Admin actualizar(Admin admin) {
        try {
            admin.setUsuario(usuarioService.actualizar(admin.getUsuario()));
            adminReository.save(admin);
            log.debug("Se actualizo el admin id " + admin.getId());

            return admin;
        } catch (NoSuchElementException | EntityNotFoundException exception) {
            throw new RecursoNoEncontradoException(exception.getMessage(), exception);
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            adminReository.deleteById(id);
            log.debug("Se elimino el admin id " + id);
        } catch (EmptyResultDataAccessException exception) {
            log.debug("El admin con id " + id + "no existía");
        }
    }
}
