package com.digitalhouse.CoachConnectBE.util;

import com.digitalhouse.CoachConnectBE.controller.caracteristica.dto.NuevoCaracteristicaDto;
import com.digitalhouse.CoachConnectBE.controller.categoria.dto.NuevoCategoriaDto;
import com.digitalhouse.CoachConnectBE.controller.estudiante.dto.ActualizarEstudianteDto;
import com.digitalhouse.CoachConnectBE.controller.estudiante.dto.NuevoEstudianteDto;
import com.digitalhouse.CoachConnectBE.controller.nivel.dto.NuevoNivelDto;
import com.digitalhouse.CoachConnectBE.controller.tutor.dto.ActualizarTutorDto;
import com.digitalhouse.CoachConnectBE.controller.tutor.dto.NuevoTutorDto;
import com.digitalhouse.CoachConnectBE.entity.*;

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

    public static Tutor map(NuevoTutorDto dto) {
        Tutor tutor = new Tutor();
        Usuario usuario = new Usuario();

        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setEdad(dto.getEdad());
        usuario.setEmail(dto.getEmail());
        usuario.setContactoCelular(dto.getContactoCelular());
        usuario.setFoto(dto.getFoto());

        tutor.setUsuario(usuario);
        tutor.setProfesion(dto.getProfesion());
        tutor.setDescripcion(dto.getDescripcion());

        return tutor;
    }

    public static Tutor map(ActualizarTutorDto dto, Long id) {
        Tutor tutor = new Tutor();
        Usuario usuario = new Usuario();

        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setEdad(dto.getEdad());
        usuario.setEmail(dto.getEmail());
        usuario.setContactoCelular(dto.getContactoCelular());
        usuario.setFoto(dto.getFoto());

        tutor.setId(id);
        tutor.setUsuario(usuario);
        tutor.setProfesion(dto.getProfesion());
        tutor.setDescripcion(dto.getDescripcion());

        return tutor;
    }

    public static Nivel map(NuevoNivelDto dto, Long id) {
        Nivel nivel = new Nivel();

        nivel.setId(id);
        nivel.setNombre(dto.getNombre());

        return nivel;
    }

    public static Categoria map(NuevoCategoriaDto dto, Long id) {
        Categoria categoria = new Categoria();

        categoria.setId(id);
        categoria.setNombre(dto.getNombre());

        return categoria;
    }

    public static Caracteristica map(NuevoCaracteristicaDto dto, Long id) {
        Caracteristica caracteristica = new Caracteristica();

        caracteristica.setId(id);
        caracteristica.setNombre(dto.getNombre());

        return caracteristica;
    }
}
