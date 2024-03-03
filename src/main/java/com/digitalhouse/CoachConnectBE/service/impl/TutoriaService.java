package com.digitalhouse.CoachConnectBE.service.impl;


import com.digitalhouse.CoachConnectBE.entity.Caracteristica;
import com.digitalhouse.CoachConnectBE.entity.Categoria;
import com.digitalhouse.CoachConnectBE.entity.Nivel;
import com.digitalhouse.CoachConnectBE.entity.Tutoria;
import com.digitalhouse.CoachConnectBE.repository.TutoriaRepository;
import com.digitalhouse.CoachConnectBE.service.ICaracteristicaService;
import com.digitalhouse.CoachConnectBE.service.ICategoriaService;
import com.digitalhouse.CoachConnectBE.service.INivelService;
import com.digitalhouse.CoachConnectBE.service.ITutoriaService;
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

    public Tutoria guardar(Tutoria tutoria) {
        checkSiCategoriaExiste(tutoria.getCategoriaId());
        checkSiNivelExiste(tutoria.getNivelId());
        checkSiCaracteristicasExisten(new HashSet<>(tutoria.getCaracteristicasIds()));
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
    public Tutoria actualizar(Tutoria tutoria, Set<Long> idsCaracteristicas) {
        try {
            tutoriaReository.findTutoriaById(tutoria.getId()).orElseThrow();
            checkSiCaracteristicasExisten(idsCaracteristicas);

            Set<Caracteristica> caracteristicasActualizadas = idsCaracteristicas.stream()
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

    private void checkSiCaracteristicasExisten(Set<Long> idsCaracteristicas) {
        List<Long> idsCaracteristicasActuales = caracteristicaService.listarTodos().stream()
                .map(Caracteristica::getId)
                .toList();

        if (!new HashSet<>(idsCaracteristicasActuales).containsAll(idsCaracteristicas)) {
            throw new RecursoNoEncontradoException();
        }
    }

    private void checkSiNivelExiste(Long nivelId) {
        Nivel nivel = nivelService.encontrarUnoPorId(nivelId);
        if (nivel == null) {
            throw new RecursoNoEncontradoException();
        }
    }

    private void checkSiCategoriaExiste(Long categoriaId) {
        Categoria categoria = categoriaService.encontrarUnoPorId(categoriaId);
        if (categoria == null) {
            throw new RecursoNoEncontradoException();
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
}
