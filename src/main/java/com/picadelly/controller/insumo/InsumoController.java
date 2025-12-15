package com.picadelly.controller.insumo;

import com.picadelly.dto.ApiResponse;
import com.picadelly.domain.insumo.Insumo;
import com.picadelly.service.insumo.InsumoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/insumo")
@RequiredArgsConstructor
public class InsumoController {

    private final InsumoService insumoService;

    @GetMapping
    public ApiResponse<List<Insumo>> listInsumos() {
        return ApiResponse.<List<Insumo>>builder()
                .success(true)
                .message("Lista de insumos")
                .data(insumoService.findAll())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<Insumo> getInsumo(@PathVariable UUID id) {
        return ApiResponse.<Insumo>builder()
                .success(true)
                .message("Insumo encontrado")
                .data(insumoService.findById(id)
                        .orElseThrow(() -> new RuntimeException("Insumo no encontrado")))
                .build();
    }

    @GetMapping("/nombre/{nombre}")
    public ApiResponse<Insumo> getInsumoByNombre(@PathVariable String nombre) {
        return ApiResponse.<Insumo>builder()
                .success(true)
                .message("Insumo encontrado")
                .data(insumoService.findByNombre(nombre)
                        .orElseThrow(() -> new RuntimeException("Insumo no encontrado")))
                .build();
    }

    @PostMapping
    public ApiResponse<Insumo> createInsumo(@RequestBody Insumo insumo) {
        return ApiResponse.<Insumo>builder()
                .success(true)
                .message("Insumo creado correctamente")
                .data(insumoService.saveInsumo(insumo))
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateInsumo(@PathVariable UUID id, @RequestBody Insumo insumo) {
        try {
            insumoService.updateInsumo(id, insumo);
            return ResponseEntity.ok("Insumo actualizado con éxito");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteInsumo(@PathVariable UUID id) {
        insumoService.deleteInsumo(id);

        return ApiResponse.<Void>builder()
                .success(true)
                .message("Insumo eliminado correctamente")
                .build();
    }
}
