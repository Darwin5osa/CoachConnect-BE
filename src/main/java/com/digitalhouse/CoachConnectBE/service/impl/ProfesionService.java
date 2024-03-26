package com.digitalhouse.CoachConnectBE.service.impl;


import com.digitalhouse.CoachConnectBE.entity.Profesion;
import com.digitalhouse.CoachConnectBE.repository.ProfesionRepository;
import com.digitalhouse.CoachConnectBE.service.IProfesionService;
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
public class ProfesionService implements IProfesionService {

    private final ProfesionRepository profesionReository;

    public Profesion guardar(Profesion profesion) {
        profesion = profesionReository.save(profesion);
        log.debug("Se guardo el profesion id " + profesion.getId());
        return profesion;
    }

    public List<Profesion> listarTodos() {
        try {
            return profesionReository.findAll();
        } catch (NoSuchElementException | EntityNotFoundException exception) {
            throw new RecursoNoEncontradoException(exception.getMessage(), exception);
        }
    }

    @Override
    public Profesion actualizar(Profesion profesion) {
        try {
            Integer elementosModificados = profesionReository.update(profesion.getId(), profesion.getNombre());
            checkearCantidadModificacion(elementosModificados);
            log.debug("Se actualizo el profesion id " + profesion.getId());

            return profesion;
        } catch (NoSuchElementException | EntityNotFoundException exception) {
            throw new RecursoNoEncontradoException(exception.getMessage(), exception);
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            profesionReository.deleteById(id);
            log.debug("Se elimino el profesion id " + id);
        } catch (EmptyResultDataAccessException exception) {
            log.debug("El profesion con id " + id + "no existía");
        } catch (DataIntegrityViolationException exception) {
            throw new RecursoConDependenciasException();
        }
    }

    @Override
    public Profesion encontrarUnoPorId(Long profesionId) {
        return profesionReository.findProfesionById(profesionId).orElse(null);
    }
}
