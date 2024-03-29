package com.digitalhouse.CoachConnectBE.controller.admin;

import com.digitalhouse.CoachConnectBE.controller.RoutePaths;
import com.digitalhouse.CoachConnectBE.controller.admin.dto.ActualizarAdminDto;
import com.digitalhouse.CoachConnectBE.controller.admin.dto.AdminResultadoDto;
import com.digitalhouse.CoachConnectBE.controller.admin.dto.NuevoAdminDto;
import com.digitalhouse.CoachConnectBE.entity.Admin;
import com.digitalhouse.CoachConnectBE.service.IAdminService;
import com.digitalhouse.CoachConnectBE.util.Mapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(RoutePaths.ADMIN)
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@CrossOrigin(origins = { "https://www.coachconnect.tech", "http://localhost:5173" })
public class AdminController {
    private final IAdminService adminService;
    private final ObjectMapper mapper;

    @GetMapping()
    public ResponseEntity<List<AdminResultadoDto>> listar() {
        return ResponseEntity.ok(adminService.listarTodos()
                .stream()
                .map(admin -> mapper.convertValue(admin, AdminResultadoDto.class))
                .toList());
    }

    @PostMapping()
    public ResponseEntity<AdminResultadoDto> guardar(@RequestBody NuevoAdminDto nuevoAdminDto) {
        log.debug("Se recibio: " + nuevoAdminDto.getUsername() + " para guardar");

        Admin admin = adminService.guardar(Mapper.map(nuevoAdminDto));
        return ResponseEntity.ok(mapper.convertValue(admin, AdminResultadoDto.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdminResultadoDto> actualizar(@RequestBody ActualizarAdminDto adminDto, @PathVariable Long id) {
        log.info("Se recibio admin para actualizar el admin con el id " + id);

        if (id == null) {
            log.error("El ID proporcionado es nulo.");
            return ResponseEntity.badRequest().body(null);
        }

        Admin admin = Mapper.map(adminDto, id);
        admin = adminService.actualizar(admin);
        return ResponseEntity.ok(mapper.convertValue(admin, AdminResultadoDto.class));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        log.debug("Se recibió la solicitud de eliminar el admin con el id " + id);

        if (id == null) {
            log.error("El ID proporcionado es nulo.");
            return ResponseEntity.badRequest().body(null);
        }

        adminService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}