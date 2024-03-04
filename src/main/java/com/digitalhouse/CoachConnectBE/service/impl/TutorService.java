package com.digitalhouse.CoachConnectBE.service.impl;


import com.digitalhouse.CoachConnectBE.entity.Profesion;
import com.digitalhouse.CoachConnectBE.entity.Tutor;
import com.digitalhouse.CoachConnectBE.repository.TutorRepository;
import com.digitalhouse.CoachConnectBE.service.IProfesionService;
import com.digitalhouse.CoachConnectBE.service.ITutorService;
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

import static com.digitalhouse.CoachConnectBE.service.ServiceExtension.checkearCantidadModificacion;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class TutorService implements ITutorService {

    private final TutorRepository tutorReository;
    private final IUsuarioService usuarioService;
    private final IProfesionService profesionService;

    public Tutor guardar(Tutor tutor) {
        checkSiProfesionExiste(tutor.getProfesionId());

        tutor.setUsuario(usuarioService.guardar(tutor.getUsuario()));
        tutor = tutorReository.save(tutor);
        log.debug("Se guardo el tutor id " + tutor.getId());
        return tutor;
    }

    public List<Tutor> listarTodos() {
        try {
            return tutorReository.findAll();
        } catch (NoSuchElementException | EntityNotFoundException exception) {
            throw new RecursoNoEncontradoException(exception.getMessage(), exception);
        }
    }

    @Override
    public Tutor actualizar(Tutor tutor) {
        try {
            checkSiProfesionExiste(tutor.getProfesionId());

            tutor.getUsuario().setId(tutorReository.findTutorById(tutor.getId()).orElseThrow().getUsuario().getId());

            tutor.setUsuario(usuarioService.actualizar(tutor.getUsuario()));
            Integer elementosModificados = tutorReository.update(tutor.getId(), tutor.getProfesionId(), tutor.getDescripcion());
            checkearCantidadModificacion(elementosModificados);
            log.debug("Se actualizo el tutor id " + tutor.getId());

            return tutor;
        } catch (NoSuchElementException | EntityNotFoundException exception) {
            throw new RecursoNoEncontradoException(exception.getMessage(), exception);
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            tutorReository.deleteById(id);
            log.debug("Se elimino el tutor id " + id);
        } catch (EmptyResultDataAccessException exception) {
            log.debug("El tutor con id " + id + "no existía");
        }
    }

    private void checkSiProfesionExiste(Long profesionId) {
        Profesion profesion = profesionService.encontrarUnoPorId(profesionId);
        if (profesion == null) {
            throw new RecursoNoEncontradoException();
        }
    }
}
