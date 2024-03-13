package com.digitalhouse.CoachConnectBE.controller.tutoria;

import com.digitalhouse.CoachConnectBE.controller.tutoria.dto.NuevoTutoriaDto;
import com.digitalhouse.CoachConnectBE.controller.tutoria.dto.TutoriaResultadoDto;
import com.digitalhouse.CoachConnectBE.entity.Tutoria;
import com.digitalhouse.CoachConnectBE.service.ITutoriaService;
import com.digitalhouse.CoachConnectBE.util.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tutoria")
@Slf4j
@CrossOrigin("*")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class TutoriaController {
    private final ITutoriaService tutoriaService;

    @GetMapping()
    public ResponseEntity<List<TutoriaResultadoDto>> listar() {
        return ResponseEntity.ok(tutoriaService.listarTodos()
                .stream()
                .map(Mapper::map)
                .toList());
    }

    @PostMapping()
    public ResponseEntity<TutoriaResultadoDto> guardar(@RequestBody NuevoTutoriaDto nuevoTutoriaDto) {
        log.debug("Se recibio: " + nuevoTutoriaDto.getNombre() + " para guardar en tutoria");

        Tutoria tutoriaux = Mapper.map(nuevoTutoriaDto);

        Tutoria tutoria = tutoriaService.guardar(tutoriaux);
        return ResponseEntity.ok(Mapper.map(tutoria));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TutoriaResultadoDto> actualizar(@RequestBody NuevoTutoriaDto tutoriaDto, @PathVariable Long id) {
        log.info("Se recibio tutoria para actualizar el tutoria con el id " + id);

        if (id == null) {
            log.error("El ID proporcionado es nulo.");
            return ResponseEntity.badRequest().body(null);
        }

        Tutoria tutoria = Mapper.map(tutoriaDto, id);
        tutoria = tutoriaService.actualizar(tutoria);
        return ResponseEntity.ok(Mapper.map(tutoria));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        log.debug("Se recibió la solicitud de eliminar el tutoria con el id " + id);

        if (id == null) {
            log.error("El ID proporcionado es nulo.");
            return ResponseEntity.badRequest().body(null);
        }

        tutoriaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}