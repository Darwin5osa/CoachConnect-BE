package com.digitalhouse.CoachConnectBE.service.impl;


import com.digitalhouse.CoachConnectBE.entity.*;
import com.digitalhouse.CoachConnectBE.repository.TutoriaRepository;
import com.digitalhouse.CoachConnectBE.service.*;
import com.digitalhouse.CoachConnectBE.service.exception.RecursoNoEncontradoException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class TutoriaService implements ITutoriaService {

    private final TutoriaRepository tutoriaReository;
    private final ICaracteristicaService caracteristicaService;
    private final ICategoriaService categoriaService;
    private final INivelService nivelService;
    private final ITutorService tutorService;

    public Tutoria guardar(Tutoria tutoria) {
        checkSiComponentesDeTutoriaExisten(tutoria);
        tutoria = tutoriaReository.save(tutoria);
        log.debug("Se guardo el tutoria id " + tutoria.getId());
        return tutoria;
    }

    public List<Tutoria> listarTodos() {
        try {
            return tutoriaReository.findAll();
        } catch (NoSuchElementException | EntityNotFoundException exception) {
            throw new RecursoNoEncontradoException(exception.getMessage(), exception);
        }
    }

    @Override
    public Tutoria actualizar(Tutoria tutoria) {
        try {
            tutoriaReository.findTutoriaById(tutoria.getId()).orElseThrow();
            checkSiComponentesDeTutoriaExisten(tutoria);

            Set<Caracteristica> caracteristicasActualizadas = tutoria.getCaracteristicasIds().stream()
                    .map(id -> {
                        Caracteristica c = new Caracteristica();
                        c.setId(id);
                        return c;
                    })
                    .collect(Collectors.toSet());

            tutoria.setCaracteristicas(caracteristicasActualizadas);

            tutoriaReository.save(tutoria);
            log.debug("Se actualizo el tutoria id " + tutoria.getId());

            return tutoria;
        } catch (NoSuchElementException | EntityNotFoundException exception) {
            throw new RecursoNoEncontradoException(exception.getMessage(), exception);
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            tutoriaReository.deleteById(id);
            log.debug("Se elimino el tutoria id " + id);
        } catch (EmptyResultDataAccessException exception) {
            log.debug("El tutoria con id " + id + "no existía");
        }
    }

    private void checkSiCaracteristicasExisten(Set<Long> idsCaracteristicas) {
        List<Long> idsCaracteristicasActuales = caracteristicaService.listarTodos().stream()
                .map(Caracteristica::getId)
                .toList();

        if (!new HashSet<>(idsCaracteristicasActuales).containsAll(idsCaracteristicas)) {
            throw new RecursoNoEncontradoException();
        }
    }

    private void checkSiComponentesDeTutoriaExisten(Tutoria tutoria) {
        checkSiCategoriaExiste(tutoria.getCategoriaId());
        checkSiNivelExiste(tutoria.getNivelId());
        checkSiTutorExiste(tutoria.getTutorId());
        checkSiCaracteristicasExisten(new HashSet<>(tutoria.getCaracteristicasIds()));
    }

    private void checkSiNivelExiste(Long nivelId) {
        Nivel nivel = nivelService.encontrarUnoPorId(nivelId);
        if (nivel == null) {
            log.error("Nivel no encontrada: " + nivelId);
            throw new RecursoNoEncontradoException();
        }
    }

    private void checkSiTutorExiste(Long tutorId) {
        Tutor tutor = tutorService.encontrarUnoPorId(tutorId);
        if (tutor == null) {
            log.error("Tutor no encontrada: " + tutorId);
            throw new RecursoNoEncontradoException();
        }
    }

    private void checkSiCategoriaExiste(Long categoriaId) {
        Categoria categoria = categoriaService.encontrarUnoPorId(categoriaId);
        if (categoria == null) {
            log.error("Categoria no encontrada: " + categoriaId);
            throw new RecursoNoEncontradoException();
        }
    }
}
