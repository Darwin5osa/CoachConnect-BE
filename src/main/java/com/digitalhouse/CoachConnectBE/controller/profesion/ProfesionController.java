package com.digitalhouse.CoachConnectBE.controller.profesion;

import com.digitalhouse.CoachConnectBE.controller.profesion.dto.ProfesionResultadoDto;
import com.digitalhouse.CoachConnectBE.controller.profesion.dto.NuevoProfesionDto;
import com.digitalhouse.CoachConnectBE.entity.Profesion;
import com.digitalhouse.CoachConnectBE.service.IProfesionService;
import com.digitalhouse.CoachConnectBE.util.Mapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profesion")
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@CrossOrigin(origins = { "https://www.coachconnect.tech", "http://localhost:5173" })
public class ProfesionController {
    private final IProfesionService profesionService;
    private final ObjectMapper mapper;

    @GetMapping()
    public ResponseEntity<List<ProfesionResultadoDto>> listar() {
        return ResponseEntity.ok(profesionService.listarTodos()
                .stream()
                .map(profesion -> mapper.convertValue(profesion, ProfesionResultadoDto.class))
                .toList());
    }

    @PostMapping()
    public ResponseEntity<ProfesionResultadoDto> guardar(@RequestBody NuevoProfesionDto nuevoProfesionDto) {
        log.debug("Se recibio: " + nuevoProfesionDto.getNombre() + " para guardar en profesion");

        Profesion profesion = profesionService.guardar(mapper.convertValue(nuevoProfesionDto, Profesion.class));
        return ResponseEntity.ok(mapper.convertValue(profesion, ProfesionResultadoDto.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfesionResultadoDto> actualizar(@RequestBody NuevoProfesionDto profesionDto, @PathVariable Long id) {
        log.info("Se recibio profesion para actualizar el profesion con el id " + id);

        if (id == null) {
            log.error("El ID proporcionado es nulo.");
            return ResponseEntity.badRequest().body(null);
        }

        Profesion profesion = Mapper.map(profesionDto, id);
        profesion = profesionService.actualizar(profesion);
        return ResponseEntity.ok(mapper.convertValue(profesion, ProfesionResultadoDto.class));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        log.debug("Se recibió la solicitud de eliminar el profesion con el id " + id);

        if (id == null) {
            log.error("El ID proporcionado es nulo.");
            return ResponseEntity.badRequest().body(null);
        }

        profesionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}