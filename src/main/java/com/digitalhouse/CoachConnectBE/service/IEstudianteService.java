package com.digitalhouse.CoachConnectBE.service;

import com.digitalhouse.CoachConnectBE.entity.Estudiante;

import java.util.List;

public interface IEstudianteService {
    Estudiante guardar(Estudiante estudiante);

    List<Estudiante> listarTodos();

    Estudiante actualizar(Estudiante estudiante);

    void eliminar(Long id);

    Estudiante obtenerEstudiante(Long estudianteId);

    Estudiante obtenerEstudiantePorUsuarioId(Long usuarioId);
}
