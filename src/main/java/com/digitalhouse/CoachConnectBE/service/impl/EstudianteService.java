package com.digitalhouse.CoachConnectBE.service.impl;


import com.digitalhouse.CoachConnectBE.entity.Estudiante;
import com.digitalhouse.CoachConnectBE.repository.EstudianteRepository;
import com.digitalhouse.CoachConnectBE.service.IEstudianteService;
import com.digitalhouse.CoachConnectBE.service.IUsuarioService;
import com.digitalhouse.CoachConnectBE.service.exception.RecursoConDependenciasException;
import com.digitalhouse.CoachConnectBE.service.exception.RecursoNoEncontradoException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

import static com.digitalhouse.CoachConnectBE.service.ServiceExtension.checkearCantidadModificacion;

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
            return estudianteReository.findAll().stream().filter(Estudiante::esEstudiante).toList();
        } catch (NoSuchElementException | EntityNotFoundException exception) {
            throw new RecursoNoEncontradoException(exception.getMessage(), exception);
        }
    }

    @Override
    public Estudiante actualizar(Estudiante estudiante) {
        try {
            estudiante.getUsuario().setId(estudianteReository.findEstudianteById(estudiante.getId()).orElseThrow().getUsuario().getId());

            estudiante.setUsuario(usuarioService.actualizar(estudiante.getUsuario()));
            Integer elementosModificados = estudianteReository.update(estudiante.getId(), estudiante.getNivelEducativo());
            checkearCantidadModificacion(elementosModificados);
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
        } catch (DataIntegrityViolationException exception) {
            throw new RecursoConDependenciasException();
        }
    }

    @Override
    public Estudiante obtenerEstudiante(Long estudianteId) {
        return estudianteReository.findEstudianteById(estudianteId).orElseThrow();
    }

    @Override
    public Estudiante obtenerEstudiantePorUsuarioId(Long usuarioId) {
        return estudianteReository.findEstudianteByUsuarioId(usuarioId)
                .stream().findFirst().orElse(null);
    }
}
