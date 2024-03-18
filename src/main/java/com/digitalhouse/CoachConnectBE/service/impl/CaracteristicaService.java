package com.digitalhouse.CoachConnectBE.service.impl;


import com.digitalhouse.CoachConnectBE.entity.Caracteristica;
import com.digitalhouse.CoachConnectBE.repository.CaracteristicaRepository;
import com.digitalhouse.CoachConnectBE.service.ICaracteristicaService;
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
public class CaracteristicaService implements ICaracteristicaService {

    private final CaracteristicaRepository caracteristicaReository;

    public Caracteristica guardar(Caracteristica caracteristica) {
        caracteristica = caracteristicaReository.save(caracteristica);
        log.debug("Se guardo el caracteristica id " + caracteristica.getId());
        return caracteristica;
    }

    public List<Caracteristica> listarTodos() {
        try {
            return caracteristicaReository.findAll();
        } catch (NoSuchElementException | EntityNotFoundException exception) {
            throw new RecursoNoEncontradoException(exception.getMessage(), exception);
        }
    }

    @Override
    public Caracteristica actualizar(Caracteristica caracteristica) {
        try {
            Integer elementosModificados = caracteristicaReository.update(caracteristica.getId(), caracteristica.getNombre(), caracteristica.getIcono());
            checkearCantidadModificacion(elementosModificados);
            log.debug("Se actualizo el caracteristica id " + caracteristica.getId());

            return caracteristica;
        } catch (NoSuchElementException | EntityNotFoundException exception) {
            throw new RecursoNoEncontradoException(exception.getMessage(), exception);
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            caracteristicaReository.deleteById(id);
            log.debug("Se elimino el caracteristica id " + id);
        } catch (EmptyResultDataAccessException exception) {
            log.debug("El caracteristica con id " + id + "no existía");
        }
    }
}
