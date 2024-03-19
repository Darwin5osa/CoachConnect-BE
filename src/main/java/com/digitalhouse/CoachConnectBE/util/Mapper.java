package com.digitalhouse.CoachConnectBE.util;

import com.digitalhouse.CoachConnectBE.controller.admin.dto.ActualizarAdminDto;
import com.digitalhouse.CoachConnectBE.controller.admin.dto.NuevoAdminDto;
import com.digitalhouse.CoachConnectBE.controller.caracteristica.dto.NuevoCaracteristicaDto;
import com.digitalhouse.CoachConnectBE.controller.categoria.dto.NuevoCategoriaDto;
import com.digitalhouse.CoachConnectBE.controller.estudiante.dto.ActualizarEstudianteDto;
import com.digitalhouse.CoachConnectBE.controller.estudiante.dto.NuevoEstudianteDto;
import com.digitalhouse.CoachConnectBE.controller.nivel.dto.NuevoNivelDto;
import com.digitalhouse.CoachConnectBE.controller.profesion.dto.NuevoProfesionDto;
import com.digitalhouse.CoachConnectBE.controller.tutor.dto.ActualizarTutorDto;
import com.digitalhouse.CoachConnectBE.controller.tutor.dto.NuevoTutorDto;
import com.digitalhouse.CoachConnectBE.controller.tutoria.dto.NuevoTutoriaDto;
import com.digitalhouse.CoachConnectBE.controller.tutoria.dto.TutoriaResultadoDto;
import com.digitalhouse.CoachConnectBE.entity.*;

import java.util.Set;
import java.util.stream.Collectors;

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
        usuario.setRol(RolUsuario.ESTUDIANTE.toString());

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
        usuario.setUsername(dto.getUsername());
        usuario.setPassword(dto.getPassword());

        tutor.setUsuario(usuario);
        tutor.setProfesion(new Profesion(dto.getProfesion()));
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
        tutor.setProfesion(new Profesion(dto.getProfesion()));
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

    public static Tutoria map(NuevoTutoriaDto dto, Long id) {
        Tutoria tutoria = new Tutoria();

        tutoria.setId(id);
        tutoria.setNombre(dto.getNombre());
        tutoria.setDescripcion(dto.getDescripcion());
        tutoria.setCategoria(new Categoria(dto.getCategoriaId()));
        tutoria.setNivel(new Nivel(dto.getNivelId()));
        tutoria.setTutor(new Tutor(dto.getTutorId()));
        tutoria.setCaracteristicas(getSetDeCaracteristcas(dto));

        return tutoria;
    }

    public static TutoriaResultadoDto map(Tutoria tutoria) {
        return new TutoriaResultadoDto(
                tutoria.getId(),
                tutoria.getNombre(),
                tutoria.getDescripcion(),
                tutoria.getNivelId(),
                tutoria.getCategoriaId(),
                tutoria.getTutorId(),
                tutoria.getCaracteristicasIds()
        );
    }

    public static Tutoria map(NuevoTutoriaDto dto) {
        Tutoria tutoria = new Tutoria();

        tutoria.setNombre(dto.getNombre());
        tutoria.setDescripcion(dto.getDescripcion());
        tutoria.setCategoria(new Categoria(dto.getCategoriaId()));
        tutoria.setNivel(new Nivel(dto.getNivelId()));
        tutoria.setTutor(new Tutor(dto.getTutorId()));
        tutoria.setCaracteristicas(getSetDeCaracteristcas(dto));

        return tutoria;
    }


    public static Set<Caracteristica> getSetDeCaracteristcas(NuevoTutoriaDto dto) {
        return dto.getCaracteristicas().stream()
                .map(Caracteristica::new).collect(Collectors.toSet());
    }

    public static Profesion map(NuevoProfesionDto dto, Long id) {
        Profesion profesion = new Profesion();

        profesion.setId(id);
        profesion.setNombre(dto.getNombre());

        return profesion;
    }

    public static Admin map(NuevoAdminDto dto) {
        Admin admin = new Admin();
        Usuario usuario = new Usuario();

        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setEdad(dto.getEdad());
        usuario.setEmail(dto.getEmail());
        usuario.setContactoCelular(dto.getContactoCelular());
        usuario.setFoto(dto.getFoto());
        usuario.setUsername(dto.getUsername());
        usuario.setPassword(dto.getPassword());
        usuario.setRol(RolUsuario.ADMIN.toString());

        admin.setUsuario(usuario);

        return admin;
    }

    public static Admin map(ActualizarAdminDto dto, Long id) {
        Admin admin = new Admin();
        Usuario usuario = new Usuario();

        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setEdad(dto.getEdad());
        usuario.setEmail(dto.getEmail());
        usuario.setContactoCelular(dto.getContactoCelular());
        usuario.setFoto(dto.getFoto());

        admin.setUsuario(usuario);
        admin.setId(id);

        return admin;
    }
}
