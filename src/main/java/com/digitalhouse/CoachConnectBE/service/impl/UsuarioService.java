package com.digitalhouse.CoachConnectBE.service.impl;


import com.digitalhouse.CoachConnectBE.entity.RolUsuario;
import com.digitalhouse.CoachConnectBE.entity.Usuario;
import com.digitalhouse.CoachConnectBE.repository.UsuarioRepository;
import com.digitalhouse.CoachConnectBE.service.IUsuarioService;
import com.digitalhouse.CoachConnectBE.service.exception.RecursoNoEncontradoException;
import com.digitalhouse.CoachConnectBE.service.exception.UsuarioConUsernameYaExisteException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.digitalhouse.CoachConnectBE.service.ServiceExtension.checkearCantidadModificacion;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UsuarioService implements IUsuarioService {

    private final UsuarioRepository usuarioReository;

    public Usuario guardar(Usuario usuario) {
        checkearUserName(usuario.getUsername());
        usuario = usuarioReository.save(usuario);
        log.info("Se guardo el usuario id " + usuario.getId());
        return usuario;
    }

    private void checkearUserName(String username) {
        if (usuarioReository.findUsuarioByUsername(username).isPresent()) {
            throw new UsuarioConUsernameYaExisteException(username);
        }
    }

    public List<Usuario> listarTodos() {
        try {
            return usuarioReository.findAll();
        } catch (NoSuchElementException | EntityNotFoundException exception) {
            throw new RecursoNoEncontradoException(exception.getMessage(), exception);
        }
    }

    @Override
    public Usuario actualizar(Usuario usuario) {
        try {
            Integer elementosModificados = usuarioReository.update(usuario.getId(), usuario.getNombre(), usuario.getApellido(), usuario.getEdad(), usuario.getEmail(), usuario.getPassword(), usuario.getContactoCelular());
            checkearCantidadModificacion(elementosModificados);
            log.info("Se actualizo el usuario id " + usuario.getId());
            return usuario;
        } catch (NoSuchElementException | EntityNotFoundException exception) {
            throw new RecursoNoEncontradoException(exception.getMessage(), exception);
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            usuarioReository.deleteById(id);
            log.info("Se elimino el usuario id " + id);
        } catch (EmptyResultDataAccessException exception) {
            log.info("El usuario con id " + id + "no existía");
        }
    }

    @Override
    public Optional<Usuario> login(String email, String password) {
        return usuarioReository.findUsuarioByEmailAndPassword(email, password);
    }

    @Override
    public void cambiarRol(String username, RolUsuario rol) {
        try {
            Integer elementosModificados = usuarioReository.cambiarRol(username, rol.toString());
            checkearCantidadModificacion(elementosModificados);
            log.warn("Se actualizo el rol del usuario " + username + " al rol de " + rol);
        } catch (NoSuchElementException | EntityNotFoundException exception) {
            throw new RecursoNoEncontradoException(exception.getMessage(), exception);
        }
    }
}
