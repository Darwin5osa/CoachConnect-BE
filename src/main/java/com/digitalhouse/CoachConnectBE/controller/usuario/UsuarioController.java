package com.digitalhouse.CoachConnectBE.controller.usuario;

import com.digitalhouse.CoachConnectBE.config.JwtService;
import com.digitalhouse.CoachConnectBE.controller.usuario.dto.UsuarioLoginDto;
import com.digitalhouse.CoachConnectBE.controller.usuario.dto.UsuarioToken;
import com.digitalhouse.CoachConnectBE.entity.Usuario;
import com.digitalhouse.CoachConnectBE.service.IUsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Slf4j
public class UsuarioController {

    private final JwtService jwtService;
    private final IUsuarioService usuarioService;

    @PostMapping()
    public ResponseEntity<?> login(@RequestBody UsuarioLoginDto dto) {
        Optional<Usuario> usuario = usuarioService.login(dto.getEmail(), dto.getPassword());
        log.info(usuario.orElseThrow().getUsername());
        if (usuario.isPresent()) {
            String token = jwtService.generateToken(usuario.get().getUsername(), usuario.get().getRol().toString());
            return ResponseEntity.ok(new UsuarioToken(token));
        }
        return ResponseEntity.status(401).build();
    }
}

