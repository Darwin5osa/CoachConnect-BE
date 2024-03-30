package com.digitalhouse.CoachConnectBE.service.impl;

import com.digitalhouse.CoachConnectBE.entity.Reserva;
import com.digitalhouse.CoachConnectBE.repository.ReservaRepository;
import com.digitalhouse.CoachConnectBE.service.IEstudianteService;
import com.digitalhouse.CoachConnectBE.service.IReservaService;
import com.digitalhouse.CoachConnectBE.service.ITutoriaService;
import com.digitalhouse.CoachConnectBE.service.exception.RecursoConDependenciasException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.digitalhouse.CoachConnectBE.service.ServiceExtension.checkearCantidadModificacion;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ReservaService implements IReservaService {

    private final ReservaRepository reservaRepository;
    private final IEstudianteService estudianteService;
    private final ITutoriaService tutoriaService;

    @Override
    public Reserva guardar(Reserva reserva) {
        reserva = reservaRepository.save(reserva);
        log.debug("Se agrego una reserva para la tutoria: " + reserva.getTutoriaId() + " para el estudiante: " + reserva.getEstudianteId());
        reserva.setTutoria(tutoriaService.obtenerTutoria(reserva.getTutoriaId()));
        reserva.setEstudiante(estudianteService.obtenerEstudiante(reserva.getEstudianteId()));

        return reserva;
    }

    @Override
    public List<Reserva> listarPorIdEstudiante(Long id) {
        log.info("Se listaran todas las reservas del estudiante: " + id);
        return reservaRepository.findByEstudianteId(id);
    }

    @Override
    public Reserva actualizar(Reserva reserva) {
        Integer elementosModificados = reservaRepository.update(reserva.getId(), reserva.getFechaInicio().atStartOfDay(), reserva.getFechaFin().atStartOfDay(), reserva.getHorasReservadas());
        checkearCantidadModificacion(elementosModificados);

        Reserva reservaInDBb = reservaRepository.findReservaById(reserva.getId()).orElseThrow();
        reserva.setEstudiante(estudianteService.obtenerEstudiante(reservaInDBb.getEstudianteId()));
        reserva.setTutoria(tutoriaService.obtenerTutoria(reservaInDBb.getTutoriaId()));

        return reserva;
    }

    @Override
    public void eliminar(Long id) {
        try {
            reservaRepository.deleteById(id);
            log.debug("Se elimino la reserva id " + id);
        } catch (EmptyResultDataAccessException exception) {
            log.debug("La reserva con id " + id + " no existía");
        } catch (DataIntegrityViolationException exception) {
            throw new RecursoConDependenciasException();
        }
    }
}
