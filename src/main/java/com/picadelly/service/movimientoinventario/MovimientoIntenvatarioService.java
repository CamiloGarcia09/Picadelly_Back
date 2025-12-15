package com.picadelly.service.movimientoinventario;

import com.picadelly.domain.insumo.Insumo;
import com.picadelly.domain.movimientoinventario.MovimientoInventario;
import com.picadelly.domain.movimientoinventario.TipoMovimiento;
import com.picadelly.repository.insumo.InsumoRepository;
import com.picadelly.repository.movimientoinventario.MovimientoInventarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class MovimientoIntenvatarioService {

    private final MovimientoInventarioRepository movimientoInventarioRepository;
    private final InsumoRepository insumoRepository;

    public MovimientoIntenvatarioService(final MovimientoInventarioRepository movimientoInventarioRepository,
                                         final InsumoRepository insumoRepository) {
        this.movimientoInventarioRepository = movimientoInventarioRepository;
        this.insumoRepository = insumoRepository;
    }

    public List<MovimientoInventario> findAll() {
        return movimientoInventarioRepository.findAll();
    }

    public Optional<MovimientoInventario> findById(final UUID id) {
        return movimientoInventarioRepository.findById(id);
    }


    @Transactional
    public MovimientoInventario saveMovimientoInventario(final MovimientoInventario movimientoInventario) {
        Insumo insumo = insumoRepository.findById(movimientoInventario.getInsumo().getId())
                .orElseThrow(() -> new RuntimeException("Insumo no encontrado"));

        float cantidad = movimientoInventario.getCantidad();

        if (movimientoInventario.getMovimiento() == TipoMovimiento.ENTRADA) {
            insumo.setStockActual(insumo.getStockActual() + cantidad);
        }

        if (movimientoInventario.getMovimiento() == TipoMovimiento.SALIDA) {
            if (insumo.getStockActual() < cantidad) {
                throw new RuntimeException("Stock insuficiente");
            }
            insumo.setStockActual(insumo.getStockActual() - cantidad);
        }

        insumoRepository.save(insumo);
        return movimientoInventarioRepository.save(movimientoInventario);


    }
}
