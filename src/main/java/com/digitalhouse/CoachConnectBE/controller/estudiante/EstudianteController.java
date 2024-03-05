package com.digitalhouse.CoachConnectBE.controller.estudiante;

import com.digitalhouse.CoachConnectBE.controller.estudiante.dto.ActualizarEstudianteDto;
import com.digitalhouse.CoachConnectBE.controller.estudiante.dto.ActualizarEstudianteResultadoDto;
import com.digitalhouse.CoachConnectBE.controller.estudiante.dto.EstudianteResultadoDto;
import com.digitalhouse.CoachConnectBE.controller.estudiante.dto.NuevoEstudianteDto;
import com.digitalhouse.CoachConnectBE.entity.Estudiante;
import com.digitalhouse.CoachConnectBE.service.IEstudianteService;
import com.digitalhouse.CoachConnectBE.util.Mapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudiante")
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class EstudianteController {
    private final IEstudianteService estudianteService;
    private final ObjectMapper mapper;

    @GetMapping()
    public ResponseEntity<List<EstudianteResultadoDto>> listar() {
        return ResponseEntity.ok(estudianteService.listarTodos()
                .stream()
                .map(estudiante -> mapper.convertValue(estudiante, EstudianteResultadoDto.class))
                .toList());
    }

    @PostMapping()
    public ResponseEntity<EstudianteResultadoDto> guardar(@RequestBody NuevoEstudianteDto nuevoEstudianteDto) {
        log.debug("Se recibio: " + nuevoEstudianteDto.getUsername() + " para guardar");

        Estudiante estudiante = estudianteService.guardar(Mapper.map(nuevoEstudianteDto));
        return ResponseEntity.ok(mapper.convertValue(estudiante, EstudianteResultadoDto.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActualizarEstudianteResultadoDto> actualizar(@RequestBody ActualizarEstudianteDto estudianteDto, @PathVariable Long id) {
        log.info("Se recibio estudiante para actualizar el estudiante con el id " + id);

        if (id == null) {
            log.error("El ID proporcionado es nulo.");
            return ResponseEntity.badRequest().body(null);
        }

        Estudiante estudiante = Mapper.map(estudianteDto, id);
        estudiante = estudianteService.actualizar(estudiante);
        return ResponseEntity.ok(mapper.convertValue(estudiante, ActualizarEstudianteResultadoDto.class));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        log.debug("Se recibió la solicitud de eliminar el estudiante con el id " + id);

        if (id == null) {
            log.error("El ID proporcionado es nulo.");
            return ResponseEntity.badRequest().body(null);
        }

        estudianteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}