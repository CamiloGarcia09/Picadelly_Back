package com.picadelly.controller.insumo;

import com.picadelly.dto.ApiResponse;
import com.picadelly.domain.insumo.Insumo;
import com.picadelly.dto.ApiResponsePagination;
import com.picadelly.service.insumo.InsumoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/insumo")
@RequiredArgsConstructor
public class InsumoController {

    private final InsumoService insumoService;

    @GetMapping("/all")
    public ApiResponsePagination<List<Insumo>> getAllInsumos(
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Insumo> result = insumoService.findAll(pageable);

        return ApiResponsePagination.<List<Insumo>>builder()
                .success(true)
                .message("Lista de insumos")
                .totalPages(result.getTotalPages())
                .pageNumber(result.getNumber())
                .pageSize(result.getSize())
                .totalRecords(result.getTotalElements())
                .data(result.getContent())
                .build();
    }

    @GetMapping("/tipoinsumo/{nombreTipoInsumo}")
    public ApiResponsePagination<List<Insumo>> getAllInsumosByTipoInsumo(@PathVariable String nombreTipoInsumo,
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Insumo> result = insumoService.findByTipoInsumo(nombreTipoInsumo, pageable);

        return ApiResponsePagination.<List<Insumo>>builder()
                .success(true)
                .message("Lista de insumos")
                .totalPages(result.getTotalPages())
                .pageNumber(result.getNumber())
                .pageSize(result.getSize())
                .totalRecords(result.getTotalElements())
                .data(result.getContent())
                .build();
    }

    @GetMapping()
    public ApiResponsePagination<List<Insumo>> getInsumoByFilter(
            @RequestParam(required = false) UUID tipoInsumoID,
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Insumo> result = insumoService.buscarConFiltros(tipoInsumoID, pageable);

        return ApiResponsePagination.<List<Insumo>>builder()
                .success(true)
                .message("Lista de insumos de inventario")
                .totalPages(result.getTotalPages())
                .pageNumber(result.getNumber())
                .pageSize(result.getSize())
                .totalRecords(result.getTotalElements())
                .data(result.getContent())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<Insumo> getInsumoById(@PathVariable UUID id) {
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
