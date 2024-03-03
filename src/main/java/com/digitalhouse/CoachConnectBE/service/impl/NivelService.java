package com.digitalhouse.CoachConnectBE.service.impl;


import com.digitalhouse.CoachConnectBE.entity.Nivel;
import com.digitalhouse.CoachConnectBE.repository.NivelRepository;
import com.digitalhouse.CoachConnectBE.service.INivelService;
import com.digitalhouse.CoachConnectBE.service.exception.RecursoNoEncontradoException;
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
public class NivelService implements INivelService {

    private final NivelRepository nivelReository;

    public Nivel guardar(Nivel nivel) {
        nivel = nivelReository.save(nivel);
        log.debug("Se guardo el nivel id " + nivel.getId());
        return nivel;
    }

    public List<Nivel> listarTodos() {
        try {
            return nivelReository.findAll();
        } catch (NoSuchElementException | EntityNotFoundException exception) {
            throw new RecursoNoEncontradoException(exception.getMessage(), exception);
        }
    }

    @Override
    public Nivel actualizar(Nivel nivel) {
        try {
            Integer elementosModificados = nivelReository.update(nivel.getId(), nivel.getNombre());
            checkearCantidadModificacion(elementosModificados);
            log.info("Se actualizo el nivel id " + nivel.getId());

            return nivel;
        } catch (NoSuchElementException | EntityNotFoundException exception) {
            throw new RecursoNoEncontradoException(exception.getMessage(), exception);
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            nivelReository.deleteById(id);
            log.debug("Se elimino el nivel id " + id);
        } catch (EmptyResultDataAccessException exception) {
            log.debug("El nivel con id " + id + "no existía");
        }
    }

    @Override
    public Nivel encontrarUnoPorId(Long nivelId) {
        return nivelReository.findNivelById(nivelId).orElse(null);
    }
}
