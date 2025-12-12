package com.picadelly.controller.tipoinsumo;

import com.picadelly.domain.tipoinsumo.TipoInsumo;
import com.picadelly.service.tipoinsumo.TipoInsumoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tipoinsumo")
public class TipoInsumoController {

    private final TipoInsumoService tipoInsumoService;

    public TipoInsumoController(final TipoInsumoService tipoInsumoService) {
        this.tipoInsumoService = tipoInsumoService;
    }

    @GetMapping
    public ResponseEntity<List<TipoInsumo>> getAllTipoInsumo() {
        List<TipoInsumo> tipoInsumos = tipoInsumoService.findAll();
        if (tipoInsumos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(tipoInsumos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoInsumo> getTipoInsumoById(@PathVariable UUID id) {
        return tipoInsumoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{nombre}/tipoinsumo")
    public ResponseEntity<TipoInsumo> getTipoInsumoByName(@PathVariable String nombre) {
        return tipoInsumoService.findByNombre(nombre)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> createTipoInsumo(@RequestBody TipoInsumo tipoInsumo) {
        try {
            tipoInsumoService.saveTipoInsumo(tipoInsumo);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Tipo Insumo creado exitosamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTipoInsumo(@PathVariable UUID id, @RequestBody TipoInsumo tipoInsumo) {
        try {
            tipoInsumoService.updateTipoInsumo(id, tipoInsumo);
            return ResponseEntity.ok("Tipo Insumo actulizado con exito");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTipoInsumo(@PathVariable UUID id) {
        try {
            tipoInsumoService.deleteTipoInsumo(id);
            return ResponseEntity.ok("Tipo Insumo eliminado correctamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
