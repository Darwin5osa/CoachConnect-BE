package com.digitalhouse.CoachConnectBE.service.impl;


import com.digitalhouse.CoachConnectBE.entity.Categoria;
import com.digitalhouse.CoachConnectBE.repository.CategoriaRepository;
import com.digitalhouse.CoachConnectBE.service.ICategoriaService;
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
public class CategoriaService implements ICategoriaService {

    private final CategoriaRepository categoriaReository;

    public Categoria guardar(Categoria categoria) {
        categoria = categoriaReository.save(categoria);
        log.debug("Se guardo el categoria id " + categoria.getId());
        return categoria;
    }

    public List<Categoria> listarTodos() {
        try {
            return categoriaReository.findAll();
        } catch (NoSuchElementException | EntityNotFoundException exception) {
            throw new RecursoNoEncontradoException(exception.getMessage(), exception);
        }
    }

    @Override
    public Categoria actualizar(Categoria categoria) {
        try {
            Integer elementosModificados = categoriaReository.update(categoria.getId(), categoria.getNombre());
            checkearCantidadModificacion(elementosModificados);
            log.debug("Se actualizo el categoria id " + categoria.getId());

            return categoria;
        } catch (NoSuchElementException | EntityNotFoundException exception) {
            throw new RecursoNoEncontradoException(exception.getMessage(), exception);
        }
    }

    @Override
    public void eliminar(Long id) {
        try {
            categoriaReository.deleteById(id);
            log.debug("Se elimino el categoria id " + id);
        } catch (EmptyResultDataAccessException exception) {
            log.debug("El categoria con id " + id + "no existía");
        } catch (DataIntegrityViolationException exception) {
            throw new RecursoConDependenciasException();
        }
    }

    @Override
    public Categoria encontrarUnoPorId(Long categoriaId) {
        return categoriaReository.findCategoriaById(categoriaId).orElse(null);
    }
}
