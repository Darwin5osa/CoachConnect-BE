package com.digitalhouse.CoachConnectBE.controller.nivel;

import com.digitalhouse.CoachConnectBE.controller.nivel.dto.NivelResultadoDto;
import com.digitalhouse.CoachConnectBE.controller.nivel.dto.NuevoNivelDto;
import com.digitalhouse.CoachConnectBE.entity.Nivel;
import com.digitalhouse.CoachConnectBE.service.INivelService;
import com.digitalhouse.CoachConnectBE.util.Mapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nivel")
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@CrossOrigin(origins = { "https://www.coachconnect.tech", "http://localhost:5173" })
public class NivelController {
    private final INivelService nivelService;
    private final ObjectMapper mapper;

    @GetMapping()
    public ResponseEntity<List<NivelResultadoDto>> listar() {
        return ResponseEntity.ok(nivelService.listarTodos()
                .stream()
                .map(nivel -> mapper.convertValue(nivel, NivelResultadoDto.class))
                .toList());
    }

    @PostMapping()
    public ResponseEntity<NivelResultadoDto> guardar(@RequestBody NuevoNivelDto nuevoNivelDto) {
        log.debug("Se recibio: " + nuevoNivelDto.getNombre() + " para guardar en nivel");

        Nivel nivel = nivelService.guardar(mapper.convertValue(nuevoNivelDto, Nivel.class));
        return ResponseEntity.ok(mapper.convertValue(nivel, NivelResultadoDto.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NivelResultadoDto> actualizar(@RequestBody NuevoNivelDto nivelDto, @PathVariable Long id) {
        log.info("Se recibio nivel para actualizar el nivel con el id " + id);

        if (id == null) {
            log.error("El ID proporcionado es nulo.");
            return ResponseEntity.badRequest().body(null);
        }

        Nivel nivel = Mapper.map(nivelDto, id);
        nivel = nivelService.actualizar(nivel);
        return ResponseEntity.ok(mapper.convertValue(nivel, NivelResultadoDto.class));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        log.debug("Se recibió la solicitud de eliminar el nivel con el id " + id);

        if (id == null) {
            log.error("El ID proporcionado es nulo.");
            return ResponseEntity.badRequest().body(null);
        }

        nivelService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}