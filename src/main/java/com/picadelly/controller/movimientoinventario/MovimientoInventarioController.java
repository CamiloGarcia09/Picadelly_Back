package com.picadelly.controller.movimientoinventario;

import com.picadelly.domain.movimientoinventario.MovimientoInventario;
import com.picadelly.service.movimientoinventario.MovimientoIntenvatarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/movimientoinventario")
public class MovimientoInventarioController {

    private final MovimientoIntenvatarioService movimientoIntenvatarioService;

    public MovimientoInventarioController(final MovimientoIntenvatarioService movimientoIntenvatarioService) {
        this.movimientoIntenvatarioService = movimientoIntenvatarioService;
    }

    @GetMapping
    public ResponseEntity<List<MovimientoInventario>> getAllMovimientoInventario() {
        List<MovimientoInventario> movimientoInventario = movimientoIntenvatarioService.findAll();
        if (movimientoInventario.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(movimientoInventario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimientoInventario> getMovimientoInventarioById(@PathVariable UUID id) {
        return movimientoIntenvatarioService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> createMovimientoInventario(@RequestBody MovimientoInventario movimientoInventario) {
        try {
            movimientoIntenvatarioService.saveMovimientoInventario(movimientoInventario);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("El Movimiento en el Inventario fue creado exitosamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}

