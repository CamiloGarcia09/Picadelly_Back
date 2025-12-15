package com.picadelly.controller.tipoinsumo;

import com.picadelly.domain.tipoinsumo.TipoInsumo;
import com.picadelly.dto.ApiResponse;
import com.picadelly.service.tipoinsumo.TipoInsumoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tipoinsumo")
@RequiredArgsConstructor
public class TipoInsumoController {

    private final TipoInsumoService tipoInsumoService;
    private final JsonMapper.Builder builder;

    @GetMapping
    public ApiResponse<List<TipoInsumo>> getAllTipoInsumo() {
        return ApiResponse.<List<TipoInsumo>>builder()
                .success(true)
                .message("Lista de tipos de insumo")
                .data(tipoInsumoService.findAll())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<TipoInsumo> getTipoInsumoById(@PathVariable UUID id) {
        return ApiResponse.<TipoInsumo>builder()
                .success(true)
                .message("Tipo de insumo encontrado")
                .data(tipoInsumoService.findById(id).orElseThrow(() -> new RuntimeException("Tipo de insumo no encontrado")))
                .build();
    }

    @GetMapping("/nombre/{nombre}")
    public ApiResponse<TipoInsumo> getTipoInsumoByName(@PathVariable String nombre) {
        return ApiResponse.<TipoInsumo>builder()
                .success(true)
                .message("Tipo de insumo encontrado")
                .data(tipoInsumoService.findByNombre(nombre).orElseThrow(() -> new RuntimeException("Tipo de insumo no encontrado")))
                .build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<TipoInsumo> createTipoInsumo(@RequestBody TipoInsumo tipoInsumo) {
        return ApiResponse.<TipoInsumo>builder()
                .success(true)
                .message("Tipo de insumo creado exitosamente")
                .data(tipoInsumoService.saveTipoInsumo(tipoInsumo))
                .build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTipoInsumo(@PathVariable UUID id, @RequestBody TipoInsumo tipoInsumo) {
        try {
            tipoInsumoService.updateTipoInsumo(id, tipoInsumo);
            return ResponseEntity.ok("Tipo Insumo actualizado con éxito");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTipoInsumo(@PathVariable UUID id) {
        try {
            tipoInsumoService.deleteTipoInsumo(id);
            return ResponseEntity.ok("Tipo Insumo eliminado correctamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
