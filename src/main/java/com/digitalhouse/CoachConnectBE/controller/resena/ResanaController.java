package com.digitalhouse.CoachConnectBE.controller.resena;

import com.digitalhouse.CoachConnectBE.controller.RoutePaths;
import com.digitalhouse.CoachConnectBE.controller.resena.dto.NuevaResenaDto;
import com.digitalhouse.CoachConnectBE.controller.resena.dto.ResultadoResenaDto;
import com.digitalhouse.CoachConnectBE.entity.Resena;
import com.digitalhouse.CoachConnectBE.service.IResenaService;
import com.digitalhouse.CoachConnectBE.util.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@CrossOrigin(origins = { "https://www.coachconnect.tech", "http://localhost:5173" })
public class ResanaController {
    private final IResenaService resenaService;

    @PostMapping(path = RoutePaths.TUTORIA_RESENA)
    public ResponseEntity<ResultadoResenaDto> guardar(@RequestBody NuevaResenaDto nuevaResenaDto, @PathVariable Long id) {
        log.debug("Se recibio reseña para la tutoria: " + id + " para guardar");

        Resena resena = resenaService.guardar(Mapper.map(nuevaResenaDto, id));
        return ResponseEntity.ok(Mapper.map(resena));
    }

    @GetMapping(path = RoutePaths.TUTORIA_RESENA)
    public ResponseEntity<List<ResultadoResenaDto>> listarPorTutoria(@PathVariable Long id) {
        return ResponseEntity.ok(resenaService.listarPorIdTutoria(id)
                .stream()
                .map(Mapper::map)
                .toList());
    }

    @GetMapping(path = RoutePaths.ESTUDIANTE_RESENA)
    public ResponseEntity<List<ResultadoResenaDto>> listarPorEstudiante(@PathVariable Long id) {
        return ResponseEntity.ok(resenaService.listarPorIdEstudiante(id)
                .stream()
                .map(Mapper::map)
                .toList());
    }

    @DeleteMapping(path = RoutePaths.TUTORIA_RESENA)
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        log.debug("Se recibió la solicitud de eliminar reseña con el id " + id);

        if (id == null) {
            log.error("El ID proporcionado es nulo.");
            return ResponseEntity.badRequest().body(null);
        }

        resenaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
