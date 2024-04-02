package com.digitalhouse.CoachConnectBE.service.impl;


import com.digitalhouse.CoachConnectBE.entity.*;
import com.digitalhouse.CoachConnectBE.repository.AdminRepository;
import com.digitalhouse.CoachConnectBE.repository.EstudianteRepository;
import com.digitalhouse.CoachConnectBE.repository.TutorRepository;
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
    private final EstudianteRepository estudianteRepository;
    private final AdminRepository adminRepository;
    private final TutorRepository tutorRepository;

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
            return usuarioReository.findAll().stream().filter(usuario -> !usuario.getRol().equals(RolUsuario.TUTOR.name())).toList();
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
        Optional<Usuario> usuario = usuarioReository.findUsuarioByEmailAndPassword(email, password);
        if (!usuario.orElseThrow().getHabilitado()) {
            return Optional.empty();
        }
        return usuario;
    }

    @Override
    public void cambiarRol(String username, RolUsuario rol) {
        try {
            Integer elementosModificados = usuarioReository.cambiarRol(username, rol.toString());
            checkearCantidadModificacion(elementosModificados);

            Usuario usuario = usuarioReository.findUsuarioByUsername(username).orElseThrow();

            switch (rol) {
                case ADMIN -> agregarDatosAdmin(usuario);
                case ESTUDIANTE -> agregarDatosEstudiante(usuario);
                case TUTOR -> agregarDatosTutor(usuario);
            }

            log.warn("Se actualizo el rol del usuario " + username + " al rol de " + rol);
        } catch (NoSuchElementException | EntityNotFoundException exception) {
            throw new RecursoNoEncontradoException(exception.getMessage(), exception);
        }
    }

    @Override
    public void cambiarEstado(String username, Boolean habilitado) {
        try {
            Integer elementosModificados = usuarioReository.cambiarEstado(username, habilitado);
            checkearCantidadModificacion(elementosModificados);
            log.warn("Se actualizo el estado del usuario " + username + " a " + (habilitado ? " habilitado" : " deshabilitado"));
        } catch (NoSuchElementException | EntityNotFoundException exception) {
            throw new RecursoNoEncontradoException(exception.getMessage(), exception);
        }
    }

    private void agregarDatosEstudiante(Usuario usuario) {
        Estudiante estudiante = new Estudiante();
        estudiante.setUsuario(usuario);
        estudiante.setNivelEducativo("Sin definir");
        estudiante = estudianteRepository.save(estudiante);
        log.debug("Se guardo el estudiante id " + estudiante.getId());
    }

    private void agregarDatosAdmin(Usuario usuario) {
        Admin admin = new Admin();
        admin.setUsuario(usuario);
        admin = adminRepository.save(admin);
        log.debug("Se guardo el admin id " + admin.getId());
    }

    private void agregarDatosTutor(Usuario usuario) {
        Tutor tutor = new Tutor();
        tutor.setUsuario(usuario);
        tutor.setDescripcion("Sin descripcion");
        tutor.setCalificacion(0);
        tutor.setProfesion(new Profesion(26L));
        tutor = tutorRepository.save(tutor);
        log.debug("Se guardo el tutor id " + tutor.getId());
    }
}
