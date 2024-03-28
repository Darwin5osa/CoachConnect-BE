package com.digitalhouse.CoachConnectBE.service.impl;


import com.digitalhouse.CoachConnectBE.entity.*;
import com.digitalhouse.CoachConnectBE.repository.TutoriaRepository;
import com.digitalhouse.CoachConnectBE.service.*;
import com.digitalhouse.CoachConnectBE.service.exception.RecursoConDependenciasException;
import com.digitalhouse.CoachConnectBE.service.exception.RecursoNoEncontradoException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class TutoriaService implements ITutoriaService {

    public static final boolean TUTORIA_DISPONIBLE = true;
    private final TutoriaRepository tutoriaReository;
    private final ICaracteristicaService caracteristicaService;
    private final ICategoriaService categoriaService;
    private final INivelService nivelService;
    private final ITutorService tutorService;
    private final IResenaService resenaService;

    public Tutoria guardar(Tutoria tutoria) {
        checkSiComponentesDeTutoriaExisten(tutoria);
        tutoria = tutoriaReository.save(tutoria);
        tutoria.setCalificacionPromedio(0);
        log.debug("Se guardo el tutoria id " + tutoria.getId());
        return tutoria;
    }

    public List<Tutoria> listarTodos() {
        try {
            return tutoriaReository.findAll().stream().peek(tutoria ->
                    tutoria.setCalificacionPromedio(resenaService.obtenerCalificacionPromedio(tutoria.getId()))
            ).toList();
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
            tutoria.setCalificacionPromedio(resenaService.obtenerCalificacionPromedio(tutoria.getId()));

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
        } catch (DataIntegrityViolationException exception) {
            throw new RecursoConDependenciasException();
        }
    }

    @Override
    public List<Tutoria> obtenerTutoriasDisponibles(LocalDate fechaInicio, LocalDate fechaFin) {
        List<Tutoria> todasLasTutorias = tutoriaReository.findAll().stream().toList();

        return todasLasTutorias.stream()
                .filter(tutoria -> {
                    boolean dsiponibilidad = esTutoriaDisponibleEnRango(tutoria, fechaInicio, fechaFin);
                    log.debug("tutoria " + tutoria.getId()+ " esta disponible " + dsiponibilidad);
                    return dsiponibilidad;
                })
                .peek(tutoria ->
                        tutoria.setCalificacionPromedio(resenaService.obtenerCalificacionPromedio(tutoria.getId()))
                )
                .collect(Collectors.toList());
    }

    @Override
    public Pair<Tutoria, List<Boolean>> obtenerTutoria(Long id) {
        Tutoria tutoria = tutoriaReository.findById(id).orElse(null);

        if (tutoria == null) {
            throw new RecursoNoEncontradoException();
        }

        List<Reserva> reservas = tutoria.getReservas().stream().toList();

        List<Boolean> disponibilidad = new ArrayList<>();

        int diasEnMes = YearMonth.now().lengthOfMonth();
        for (int i = 0; i < diasEnMes; i++) {
            disponibilidad.add(false);
        }

        for (Reserva reserva : reservas) {
            int diaInicioReserva = reserva.getFechaInicio().getDayOfMonth();
            int diaFinReserva = reserva.getFechaFin().getDayOfMonth();
            for (int i = diaInicioReserva - 1; i < diaFinReserva; i++) {
                disponibilidad.set(i, true);
            }
        }

        tutoria.setCalificacionPromedio(resenaService.obtenerCalificacionPromedio(tutoria.getId()));

        return new Pair<>(tutoria, disponibilidad);
    }

    private boolean esTutoriaDisponibleEnRango(Tutoria tutoria, LocalDate fechaInicio, LocalDate fechaFin) {
        List<Reserva> reservas = new ArrayList<>(tutoria.getReservas().stream()
                .filter(reserva -> {
                            log.debug("reserva id: " + reserva.getId() + " inicio " + reserva.getFechaInicio().plusDays(10) + " vs " + fechaInicio);
                            log.debug("reserva id: " + reserva.getId() + " fin " +reserva.getFechaFin().minusDays(10) + " vs " + fechaFin);
                            return (reserva.getFechaInicio().plusDays(10).isAfter(fechaInicio)) && (reserva.getFechaFin().minusDays(10).isBefore(fechaFin));
                        }
                ).toList()
        );

        reservas.sort(Comparator.comparing(Reserva::getFechaInicio));

        if (reservas.isEmpty()) {
            log.debug("Si no hay reservas, la tutoría está disponible");
            return true;
        }

        for (int i = 1; i < reservas.size(); i++) {
            LocalDate fechaFinReservaAnterior = reservas.get(i - 1).getFechaFin();
            LocalDate fechaInicioReservaActual = reservas.get(i).getFechaInicio();
            if (fechaInicioReservaActual.minusDays(1).isAfter(fechaFinReservaAnterior)) {
                log.debug("Verificamos si hay al menos dos días de disponibilidad entre reservas - " + fechaInicioReservaActual.minusDays(1).getDayOfMonth() + " -- " + fechaFinReservaAnterior);
                return TUTORIA_DISPONIBLE;
            }
        }

        if (fechaInicio.isBefore(reservas.get(0).getFechaInicio())) {
            log.debug("Verificamos si la tutoría está disponible antes de la primera reserva");
            return true;
        }

        LocalDate fechaFinUltimaReserva = reservas.get(reservas.size() - 1).getFechaFin();
        if (fechaFinUltimaReserva.plusDays(1).isBefore(fechaFin)) {
            log.debug("Verificamos si la tutoría está disponible después de la última reserva");
            return true;
        }

        log.debug("no disponible");
        return false;
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
