package com.digitalhouse.CoachConnectBE.controller.tutor;

import com.digitalhouse.CoachConnectBE.controller.RoutePaths;
import com.digitalhouse.CoachConnectBE.controller.tutor.dto.ActualizarTutorDto;
import com.digitalhouse.CoachConnectBE.controller.tutor.dto.ActualizarTutorResultadoDto;
import com.digitalhouse.CoachConnectBE.controller.tutor.dto.NuevoTutorDto;
import com.digitalhouse.CoachConnectBE.controller.tutor.dto.TutorResultadoDto;
import com.digitalhouse.CoachConnectBE.entity.Tutor;
import com.digitalhouse.CoachConnectBE.service.ITutorService;
import com.digitalhouse.CoachConnectBE.util.Mapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(RoutePaths.TUTOR)
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@CrossOrigin(origins = { "https://www.coachconnect.tech", "http://localhost:5173" })
public class TutorController {
    private final ITutorService tutorService;
    private final ObjectMapper mapper;

    @GetMapping()
    public ResponseEntity<List<TutorResultadoDto>> listar() {
        return ResponseEntity.ok(tutorService.listarTodos()
                .stream()
                .map(tutor -> mapper.convertValue(tutor, TutorResultadoDto.class))
                .toList());
    }

    @PostMapping()
    public ResponseEntity<TutorResultadoDto> guardar(@RequestBody NuevoTutorDto nuevoTutorDto) {
        log.debug("Se recibio: " + nuevoTutorDto.getUsername() + " para guardar");

        Tutor tutor = tutorService.guardar(Mapper.map(nuevoTutorDto));
        return ResponseEntity.ok(mapper.convertValue(tutor, TutorResultadoDto.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ActualizarTutorResultadoDto> actualizar(@RequestBody ActualizarTutorDto tutorDto, @PathVariable Long id) {
        log.info("Se recibio tutor para actualizar el tutor con el id " + id);

        if (id == null) {
            log.error("El ID proporcionado es nulo.");
            return ResponseEntity.badRequest().body(null);
        }

        Tutor tutor = Mapper.map(tutorDto, id);
        tutor = tutorService.actualizar(tutor);
        return ResponseEntity.ok(mapper.convertValue(tutor, ActualizarTutorResultadoDto.class));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        log.debug("Se recibió la solicitud de eliminar el tutor con el id " + id);

        if (id == null) {
            log.error("El ID proporcionado es nulo.");
            return ResponseEntity.badRequest().body(null);
        }

        tutorService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}