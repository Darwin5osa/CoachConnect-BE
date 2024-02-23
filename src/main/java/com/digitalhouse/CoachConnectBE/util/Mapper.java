package com.digitalhouse.CoachConnectBE.util;

import com.digitalhouse.CoachConnectBE.controller.estudiante.dto.ActualizarEstudianteDto;
import com.digitalhouse.CoachConnectBE.controller.estudiante.dto.EstudianteResultadoDto;
import com.digitalhouse.CoachConnectBE.controller.estudiante.dto.NuevoEstudianteDto;
import com.digitalhouse.CoachConnectBE.entity.Estudiante;
import com.digitalhouse.CoachConnectBE.entity.Usuario;

public class Mapper {
    public static Estudiante map(NuevoEstudianteDto dto) {
        Estudiante estudiante = new Estudiante();
        Usuario usuario = new Usuario();

        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setEdad(dto.getEdad());
        usuario.setEmail(dto.getEmail());
        usuario.setContactoCelular(dto.getContactoCelular());
        usuario.setFoto(dto.getFoto());
        usuario.setUsername(dto.getUsername());
        usuario.setPassword(dto.getPassword());

        estudiante.setUsuario(usuario);
        estudiante.setNivelEducativo(dto.getNivelEducativo());

        return estudiante;
    }

    public static Estudiante map(ActualizarEstudianteDto dto ,  Long id) {
        Estudiante estudiante = new Estudiante();
        Usuario usuario = new Usuario();

        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setEdad(dto.getEdad());
        usuario.setEmail(dto.getEmail());
        usuario.setContactoCelular(dto.getContactoCelular());
        usuario.setFoto(dto.getFoto());

        estudiante.setUsuario(usuario);
        estudiante.setNivelEducativo(dto.getNivelEducativo());
        estudiante.setId(id);

        return estudiante;
    }
}
