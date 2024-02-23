package com.digitalhouse.CoachConnectBE.service.impl;


import com.digitalhouse.CoachConnectBE.entity.Tutor;
import com.digitalhouse.CoachConnectBE.repository.TutorRepository;
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

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class TutorService implements ITutorService {

    private final TutorRepository tutorReository;
    private final IUsuarioService usuarioService;

    public Tutor guardar(Tutor tutor) {
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
            tutor.getUsuario().setId(tutorReository.findTutorById(tutor.getId()).orElseThrow().getUsuario().getId());

            tutor.setUsuario(usuarioService.actualizar(tutor.getUsuario()));
            tutorReository.update(tutor.getId(), tutor.getProfesion(), tutor.getDescripcion());
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
}
