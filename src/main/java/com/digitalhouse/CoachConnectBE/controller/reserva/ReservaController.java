package com.digitalhouse.CoachConnectBE.controller.reserva;


import com.digitalhouse.CoachConnectBE.controller.RoutePaths;
import com.digitalhouse.CoachConnectBE.controller.reserva.dto.ActualizarReservaDto;
import com.digitalhouse.CoachConnectBE.controller.reserva.dto.NuevaReservaDto;
import com.digitalhouse.CoachConnectBE.controller.reserva.dto.ResultadoReservaDto;
import com.digitalhouse.CoachConnectBE.entity.Reserva;
import com.digitalhouse.CoachConnectBE.service.IReservaService;
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
public class ReservaController {

    private final IReservaService reservaService;

    @PostMapping(path = RoutePaths.RESERVA)
    public ResponseEntity<ResultadoReservaDto> guardar(@RequestBody NuevaReservaDto nuevaReservaDto) {
        log.debug("Se recibio reserva para la tutoria: " + nuevaReservaDto.getTutoriaId() + " para guardar");

        Reserva reserva = reservaService.guardar(Mapper.map(nuevaReservaDto));
        return ResponseEntity.ok(Mapper.map(reserva));
    }

    @GetMapping(path = RoutePaths.ESTUDIANTE + "/{id}" + RoutePaths.RESERVA)
    public ResponseEntity<List<ResultadoReservaDto>> listarPorTutoria(@PathVariable Long id) {
        return ResponseEntity.ok(reservaService.listarPorIdEstudiante(id)
                .stream()
                .map(Mapper::map)
                .toList());
    }

    @DeleteMapping(path = RoutePaths.RESERVA + "/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        log.debug("Se recibió la solicitud de eliminar reserva con el id " + id);

        if (id == null) {
            log.error("El ID proporcionado es nulo.");
            return ResponseEntity.badRequest().body(null);
        }

        reservaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = RoutePaths.RESERVA + "/{id}")
    public ResponseEntity<ResultadoReservaDto> eliminar(@PathVariable Long id, @RequestBody ActualizarReservaDto reservaDto) {
        log.debug("Se recibió la solicitud de actualizar reserva con el id " + id);

        if (id == null) {
            log.error("El ID proporcionado es nulo.");
            return ResponseEntity.badRequest().body(null);
        }

        Reserva reserva = Mapper.map(reservaDto, id);
        reserva = reservaService.actualizar(reserva);
        return ResponseEntity.ok(Mapper.map(reserva));
    }
}
