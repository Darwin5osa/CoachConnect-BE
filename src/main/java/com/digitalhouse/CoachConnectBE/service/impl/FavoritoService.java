package com.digitalhouse.CoachConnectBE.service.impl;

import com.digitalhouse.CoachConnectBE.entity.Favorito;
import com.digitalhouse.CoachConnectBE.entity.Tutoria;
import com.digitalhouse.CoachConnectBE.repository.FavoritoRepository;
import com.digitalhouse.CoachConnectBE.service.IFavoritoService;
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
public class FavoritoService implements IFavoritoService {

    private final FavoritoRepository favoritoRepository;

    @Override
    public void guardar(Favorito favorito) {
        favorito = favoritoRepository.save(favorito);
        log.debug("Se agrego un favorito al estudiante: " + favorito.getEstudianteId());
    }

    @Override
    public List<Tutoria> listarPorIdEstudiante(Long id) {
        log.debug("Se listaran todos los favoritos del estudiante: " + id);
        return favoritoRepository.findByEstudianteId(id).stream()
                .map(Favorito::getTutoria).toList();
    }

    @Override
    public void eliminar(Long id) {
        try {
            favoritoRepository.deleteById(id);
            log.debug("Se quito un favorito id " + id);
        } catch (EmptyResultDataAccessException exception) {
            log.debug("El favorito con id " + id + "no existía");
        } catch (DataIntegrityViolationException exception) {
            throw new RecursoConDependenciasException();
        }
    }
}
