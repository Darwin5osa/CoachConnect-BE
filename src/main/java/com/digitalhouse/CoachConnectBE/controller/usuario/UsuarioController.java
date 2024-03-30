package com.digitalhouse.CoachConnectBE.controller.usuario;

import com.digitalhouse.CoachConnectBE.config.JwtService;
import com.digitalhouse.CoachConnectBE.controller.RoutePaths;
import com.digitalhouse.CoachConnectBE.controller.usuario.dto.CambioRol;
import com.digitalhouse.CoachConnectBE.controller.usuario.dto.UsuarioLoginDto;
import com.digitalhouse.CoachConnectBE.controller.usuario.dto.UsuarioToken;
import com.digitalhouse.CoachConnectBE.entity.RolUsuario;
import com.digitalhouse.CoachConnectBE.entity.Usuario;
import com.digitalhouse.CoachConnectBE.service.IUsuarioService;
import com.digitalhouse.CoachConnectBE.service.exception.RecursoNoEncontradoException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
@CrossOrigin(origins = { "https://www.coachconnect.tech", "http://localhost:5173" })
public class UsuarioController {

    private final JwtService jwtService;
    private final IUsuarioService usuarioService;

    @PostMapping(path = RoutePaths.LOGIN)
    public ResponseEntity<?> login(@RequestBody UsuarioLoginDto dto) {
        Optional<Usuario> usuario = usuarioService.login(dto.getEmail(), dto.getPassword());
        if (usuario.isPresent()) {
            String token = jwtService.generateToken(usuario.get().getUsername(), usuario.get().getRol(), usuario.get().getNombre(), usuario.get().getApellido(), usuario.get().getEmail());
            return ResponseEntity.ok(new UsuarioToken(token));
        }
        return ResponseEntity.status(401).build();
    }

    @PutMapping(path = RoutePaths.USER)
    public ResponseEntity<String> cambiarRol(@RequestBody CambioRol dto) {
        RolUsuario rol = obtenerRol(dto.getRol());
        usuarioService.cambiarRol(dto.getUsername(), rol);
        return ResponseEntity.ok().build();
    }

    private RolUsuario obtenerRol(String rolString) {
        try {
            return RolUsuario.valueOf(rolString.toUpperCase());
        } catch (IllegalArgumentException exception) {
            log.error("Se dio un error al obtener el rol " + rolString + " exception message: " + exception.getMessage());
            throw new RecursoNoEncontradoException();
        }
    }
}

