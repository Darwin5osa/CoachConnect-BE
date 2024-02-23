package com.digitalhouse.CoachConnectBE.service.impl;


import com.digitalhouse.CoachConnectBE.entity.Estudiante;
import com.digitalhouse.CoachConnectBE.repository.EstudianteRepository;
import com.digitalhouse.CoachConnectBE.service.IEstudianteService;
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
public class EstudianteService implements IEstudianteService {

    private final EstudianteRepository estudianteReository;
    private final IUsuarioService usuarioService;

    public Estudiante guardar(Estudiante estudiante) {
        estudiante.setUsuario(usuarioService.guardar(estudiante.getUsuario()));
        estudiante = estudianteReository.save(estudiante);
        log.debug("Se guardo el estudiante id " + estudiante.getId());
        return estudiante;
    }

    public List<Estudiante> listarTodos() {
        try {
            return estudianteReository.findAll();
        } catch (NoSuchElementException | EntityNotFoundException exception) {
            throw new RecursoNoEncontradoException(exception.getMessage(), exception);
        }
    }

    @Override
    public Estudiante actualizar(Estudiante estudiante) {
        try {
            estudiante.getUsuario().setId(estudianteReository.findEstudianteById(estudiante.getId()).orElseThrow().getUsuario().getId());

            estudiante.setUsuario(usuarioService.actualizar(estudiante.getUsuario()));
            estudianteReository.update(estudiante.getId(), estudiante.getNivelEducativo());
            log.debug("Se actualizo el estudiante id " + estudiante.getId());

            return estudiante;
        } catch (NoSuchElementException | EntityNotFoundException exception) {
            throw new RecursoNoEncontradoException(exception.getMessage(), exception);
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            estudianteReository.deleteById(id);
            log.debug("Se elimino el estudiante id " + id);
        } catch (EmptyResultDataAccessException exception) {
            log.debug("El estudiante con id " + id + "no existía");
        }
    }
}
