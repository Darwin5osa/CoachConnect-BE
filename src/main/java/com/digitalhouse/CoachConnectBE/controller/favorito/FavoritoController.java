package com.digitalhouse.CoachConnectBE.controller.favorito;

import com.digitalhouse.CoachConnectBE.controller.RoutePaths;
import com.digitalhouse.CoachConnectBE.controller.favorito.dto.FavoritoDto;
import com.digitalhouse.CoachConnectBE.controller.tutoria.dto.TutoriaResultadoDto;
import com.digitalhouse.CoachConnectBE.service.IFavoritoService;
import com.digitalhouse.CoachConnectBE.util.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(RoutePaths.ESTUDIANTE_FAVORITO)
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@CrossOrigin(origins = { "https://www.coachconnect.tech", "http://localhost:5173" })
public class FavoritoController {
    private final IFavoritoService favoritoService;
    
    @PostMapping
    public ResponseEntity<String> guardar(@RequestBody FavoritoDto nuevaFavoritoDto, @PathVariable Long id) {
        log.debug("Se recibio un favorito del estudiante: " + id + " para guardar");

        favoritoService.guardar(Mapper.map(nuevaFavoritoDto, id));
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<TutoriaResultadoDto>> listarPorEstudiante(@PathVariable Long id) {
        return ResponseEntity.ok(favoritoService.listarPorIdEstudiante(id)
                .stream()
                .map(Mapper::map)
                .toList());
    }

    @DeleteMapping(path = "/{tutoriaId}")
    public ResponseEntity<String> eliminar(@PathVariable Long id, @PathVariable Long tutoriaId) {
        log.debug("Se recibió la solicitud de eliminar favorito con el id " + tutoriaId + " del estudiante " + id);

        if ((tutoriaId == null) && (id == null)) {
            log.error("El ID proporcionado es nulo.");
            return ResponseEntity.badRequest().body(null);
        }

        favoritoService.eliminar(id, tutoriaId);
        return ResponseEntity.noContent().build();
    }
}
