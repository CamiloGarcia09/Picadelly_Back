package com.picadelly.controller.movimientoinventario;

import com.picadelly.domain.movimientoinventario.MovimientoInventario;
import com.picadelly.dto.ApiResponse;
import com.picadelly.service.movimientoinventario.MovimientoIntenvatarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/movimientoinventario")
@RequiredArgsConstructor
public class MovimientoInventarioController {

    private final MovimientoIntenvatarioService movimientoIntenvatarioService;


    @GetMapping
    public ApiResponse<List<MovimientoInventario>> getAllMovimientoInventario() {
        return ApiResponse.<List<MovimientoInventario>>builder()
                .success(true)
                .message("Lista de movimientos de inventario")
                .data(movimientoIntenvatarioService.findAll())
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
