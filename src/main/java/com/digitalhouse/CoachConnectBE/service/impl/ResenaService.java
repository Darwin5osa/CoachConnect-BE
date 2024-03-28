package com.digitalhouse.CoachConnectBE.service.impl;

import com.digitalhouse.CoachConnectBE.entity.Resena;
import com.digitalhouse.CoachConnectBE.repository.ResenaRepository;
import com.digitalhouse.CoachConnectBE.service.IResenaService;
import com.digitalhouse.CoachConnectBE.service.exception.RecursoConDependenciasException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ResenaService implements IResenaService {

    private final ResenaRepository resenaRepository;

    @Override
    public Resena guardar(Resena resena) {
        resena = resenaRepository.save(resena);
        log.debug("Se guardo una reseña de la tutoria id " + resena.getTutoriaId());
        return resena;
    }

    @Override
    public List<Resena> listarPorIdEstudiante(Long id) {
        log.debug("Se listaran todas las reseñas realizadas por el estudiante: " + id);
        return resenaRepository.findByEstudianteId(id);
    }

    @Override
    public List<Resena> listarPorIdTutoria(Long id) {
        log.debug("Se listaran todas las reseñas realizadas para la tutoria: " + id);
        return resenaRepository.findByTutoriaId(id);
    }

    @Override
    public void eliminar(Long id) {
        try {
            resenaRepository.deleteById(id);
            log.debug("Se elimino la reseña id " + id);
        } catch (EmptyResultDataAccessException exception) {
            log.debug("La reseña con id " + id + "no existía");
        } catch (DataIntegrityViolationException exception) {
            throw new RecursoConDependenciasException();
        }
    }
}
