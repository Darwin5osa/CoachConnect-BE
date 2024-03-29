package com.digitalhouse.CoachConnectBE.controller.caracteristica;

import com.digitalhouse.CoachConnectBE.controller.RoutePaths;
import com.digitalhouse.CoachConnectBE.controller.caracteristica.dto.CaracteristicaResultadoDto;
import com.digitalhouse.CoachConnectBE.controller.caracteristica.dto.NuevoCaracteristicaDto;
import com.digitalhouse.CoachConnectBE.entity.Caracteristica;
import com.digitalhouse.CoachConnectBE.service.ICaracteristicaService;
import com.digitalhouse.CoachConnectBE.util.Mapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(RoutePaths.CARACTERISTICA)
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@CrossOrigin(origins = { "https://www.coachconnect.tech", "http://localhost:5173" })
public class CaracteristicaController {
    private final ICaracteristicaService caracteristicaService;
    private final ObjectMapper mapper;

    @GetMapping()
    public ResponseEntity<List<CaracteristicaResultadoDto>> listar() {
        return ResponseEntity.ok(caracteristicaService.listarTodos()
                .stream()
                .map(caracteristica -> mapper.convertValue(caracteristica, CaracteristicaResultadoDto.class))
                .toList());
    }

    @PostMapping()
    public ResponseEntity<CaracteristicaResultadoDto> guardar(@RequestBody NuevoCaracteristicaDto nuevoCaracteristicaDto) {
        log.debug("Se recibio: " + nuevoCaracteristicaDto.getNombre() + " para guardar en caracteristica");

        Caracteristica caracteristica = caracteristicaService.guardar(mapper.convertValue(nuevoCaracteristicaDto, Caracteristica.class));
        return ResponseEntity.ok(mapper.convertValue(caracteristica, CaracteristicaResultadoDto.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CaracteristicaResultadoDto> actualizar(@RequestBody NuevoCaracteristicaDto caracteristicaDto, @PathVariable Long id) {
        log.info("Se recibio caracteristica para actualizar el caracteristica con el id " + id);

        if (id == null) {
            log.error("El ID proporcionado es nulo.");
            return ResponseEntity.badRequest().body(null);
        }

        Caracteristica caracteristica = Mapper.map(caracteristicaDto, id);
        caracteristica = caracteristicaService.actualizar(caracteristica);
        return ResponseEntity.ok(mapper.convertValue(caracteristica, CaracteristicaResultadoDto.class));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        log.debug("Se recibió la solicitud de eliminar el caracteristica con el id " + id);

        if (id == null) {
            log.error("El ID proporcionado es nulo.");
            return ResponseEntity.badRequest().body(null);
        }

        caracteristicaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}