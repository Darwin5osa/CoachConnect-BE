package com.digitalhouse.CoachConnectBE.controller.categoria;

import com.digitalhouse.CoachConnectBE.controller.RoutePaths;
import com.digitalhouse.CoachConnectBE.controller.categoria.dto.CategoriaResultadoDto;
import com.digitalhouse.CoachConnectBE.controller.categoria.dto.NuevoCategoriaDto;
import com.digitalhouse.CoachConnectBE.entity.Categoria;
import com.digitalhouse.CoachConnectBE.service.ICategoriaService;
import com.digitalhouse.CoachConnectBE.util.Mapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(RoutePaths.CATEGORIA)
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@CrossOrigin(origins = { "https://www.coachconnect.tech", "http://localhost:5173" })
public class CategoriaController {
    private final ICategoriaService categoriaService;
    private final ObjectMapper mapper;

    @GetMapping()
    public ResponseEntity<List<CategoriaResultadoDto>> listar() {
        return ResponseEntity.ok(categoriaService.listarTodos()
                .stream()
                .map(categoria -> mapper.convertValue(categoria, CategoriaResultadoDto.class))
                .toList());
    }

    @PostMapping()
    public ResponseEntity<CategoriaResultadoDto> guardar(@RequestBody NuevoCategoriaDto nuevoCategoriaDto) {
        log.debug("Se recibio: " + nuevoCategoriaDto.getNombre() + " para guardar en categoria");

        Categoria categoria = categoriaService.guardar(mapper.convertValue(nuevoCategoriaDto, Categoria.class));
        return ResponseEntity.ok(mapper.convertValue(categoria, CategoriaResultadoDto.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResultadoDto> actualizar(@RequestBody NuevoCategoriaDto categoriaDto, @PathVariable Long id) {
        log.info("Se recibio categoria para actualizar el categoria con el id " + id);

        if (id == null) {
            log.error("El ID proporcionado es nulo.");
            return ResponseEntity.badRequest().body(null);
        }

        Categoria categoria = Mapper.map(categoriaDto, id);
        categoria = categoriaService.actualizar(categoria);
        return ResponseEntity.ok(mapper.convertValue(categoria, CategoriaResultadoDto.class));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        log.debug("Se recibió la solicitud de eliminar el categoria con el id " + id);

        if (id == null) {
            log.error("El ID proporcionado es nulo.");
            return ResponseEntity.badRequest().body(null);
        }

        categoriaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}