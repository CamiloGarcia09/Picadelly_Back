package com.picadelly.controller.insumo;

import com.picadelly.domain.insumo.Insumo;
import com.picadelly.service.insumo.InsumoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/insumo")
public class InsumoController {

    private final InsumoService insumoService;

    public InsumoController(final InsumoService insumoService) {
        this.insumoService = insumoService;
    }

    @GetMapping
    public ResponseEntity<List<Insumo>> getAllInsumo() {
        List<Insumo> insumos = insumoService.findAll();
        if (insumos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(insumos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Insumo> getInsumoById(@PathVariable UUID id) {
        return insumoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{nombre}/insumo")
    public ResponseEntity<Insumo> getInsumoByName(@PathVariable String nombre) {
        return insumoService.findByNombre(nombre)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<String> createInsumo(@RequestBody Insumo insumo) {
        try {
            insumoService.saveInsumo(insumo);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Insumo creado exitosamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateInsumo(@PathVariable UUID id, @RequestBody Insumo insumo) {
        try {
            insumoService.updateInsumo(id, insumo);
            return ResponseEntity.ok("Insumo actulizado con exito");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTipoInsumo(@PathVariable UUID id) {
        try {
            insumoService.deleteInsumo(id);
            return ResponseEntity.ok("Insumo eliminado correctamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

