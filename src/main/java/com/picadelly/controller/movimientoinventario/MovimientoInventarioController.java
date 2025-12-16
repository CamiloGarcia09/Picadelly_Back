package com.picadelly.controller.movimientoinventario;

import com.picadelly.domain.movimientoinventario.MovimientoInventario;
import com.picadelly.domain.movimientoinventario.TipoMovimiento;
import com.picadelly.dto.ApiResponse;
import com.picadelly.dto.ApiResponsePagination;
import com.picadelly.service.movimientoinventario.MovimientoIntenvatarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/movimientoinventario")
@RequiredArgsConstructor
public class MovimientoInventarioController {

    private final MovimientoIntenvatarioService movimientoIntenvatarioService;


    @GetMapping
    public ApiResponsePagination<List<MovimientoInventario>> getAllMovimientoInventario(
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<MovimientoInventario> result = movimientoIntenvatarioService.findAll(pageable);

        return ApiResponsePagination.<List<MovimientoInventario>>builder()
                .success(true)
                .message("Lista de movimientos de inventario")
                .totalPages(result.getTotalPages())
                .pageNumber(result.getNumber())
                .pageSize(result.getSize())
                .totalRecords(result.getTotalElements())
                .data(result.getContent())
                .build();
    }

    @GetMapping("/tipomovimiento/{tipoMovimiento}")
    public ApiResponsePagination<List<MovimientoInventario>> getMovimientoInventarioByMovimiento(
            @PathVariable TipoMovimiento tipoMovimiento, @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<MovimientoInventario> result = movimientoIntenvatarioService.findByMovimiento(tipoMovimiento, pageable);

        return ApiResponsePagination.<List<MovimientoInventario>>builder()
                .success(true)
                .message("Filtro por tipo movimiento")
                .totalPages(result.getTotalPages())
                .pageNumber(result.getNumber())
                .pageSize(result.getSize())
                .totalRecords(result.getTotalElements())
                .data(result.getContent())
                .build();
    }

    @GetMapping("/insumo/{nombreInsumo}")
    public ApiResponsePagination<List<MovimientoInventario>> getMovimientoInventarioByInsumo(
            @PathVariable String nombreInsumo, @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<MovimientoInventario> result = movimientoIntenvatarioService.findByInsumo(nombreInsumo, pageable);

        return ApiResponsePagination.<List<MovimientoInventario>>builder()
                .success(true)
                .message("Filtro por insumo")
                .totalPages(result.getTotalPages())
                .pageNumber(result.getNumber())
                .pageSize(result.getSize())
                .totalRecords(result.getTotalElements())
                .data(result.getContent())
                .build();
    }

    @GetMapping("/fecha")
    public ApiResponsePagination<List<MovimientoInventario>> getByFecha(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha,
            @RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<MovimientoInventario> result = movimientoIntenvatarioService.findByFecha(fecha, pageable);

        return ApiResponsePagination.<List<MovimientoInventario>>builder()
                .success(true)
                .message("Filtro por fecha")
                .totalPages(result.getTotalPages())
                .pageNumber(result.getNumber())
                .pageSize(result.getSize())
                .totalRecords(result.getTotalElements())
                .data(result.getContent())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<MovimientoInventario> getMovimientoInventarioById(@PathVariable UUID id) {
        MovimientoInventario movimiento = movimientoIntenvatarioService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Movimiento de inventario no encontrado"));

        return ApiResponse.<MovimientoInventario>builder()
                .success(true)
                .message("Movimiento de inventario encontrado")
                .data(movimiento)
                .build();
    }

    @PostMapping
    public ApiResponse<MovimientoInventario> createMovimientoInventario(@RequestBody MovimientoInventario movimientoInventario) {
        MovimientoInventario creado = movimientoIntenvatarioService.saveMovimientoInventario(movimientoInventario);

        return ApiResponse.<MovimientoInventario>builder()
                .success(true)
                .message("Movimiento de inventario creado exitosamente")
                .data(creado)
                .build();
    }
}
