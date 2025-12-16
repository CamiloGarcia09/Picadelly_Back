package com.picadelly.service.movimientoinventario;

import com.picadelly.domain.insumo.Insumo;
import com.picadelly.domain.movimientoinventario.MovimientoInventario;
import com.picadelly.domain.movimientoinventario.TipoMovimiento;
import com.picadelly.repository.insumo.InsumoRepository;
import com.picadelly.repository.movimientoinventario.MovimientoInventarioRepository;
import com.picadelly.repository.movimientoinventario.filter.MovimientoInventarioSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;


@Transactional
@Service
public class MovimientoIntenvatarioService {

    private final MovimientoInventarioRepository movimientoInventarioRepository;
    private final InsumoRepository insumoRepository;

    public MovimientoIntenvatarioService(final MovimientoInventarioRepository movimientoInventarioRepository,
                                         final InsumoRepository insumoRepository) {
        this.movimientoInventarioRepository = movimientoInventarioRepository;
        this.insumoRepository = insumoRepository;
    }

    public Page<MovimientoInventario> findAll(final Pageable pageable) {
        return movimientoInventarioRepository.findAll(pageable);
    }

    public Page<MovimientoInventario> findByMovimiento(final TipoMovimiento movimiento, final Pageable pageable){
        return movimientoInventarioRepository.findByMovimiento(movimiento, pageable);
    }

    public Page<MovimientoInventario> findByInsumo(final String nombreInsumo, final Pageable pageable){
        return movimientoInventarioRepository.findByInsumo_Nombre(nombreInsumo, pageable);
    }

    public Page<MovimientoInventario> findByFecha(final LocalDate fecha, final Pageable pageable){
        return movimientoInventarioRepository.findByFecha(fecha, pageable);
    }

    public Optional<MovimientoInventario> findById(final UUID id) {
        return movimientoInventarioRepository.findById(id);
    }

    public Page<MovimientoInventario> buscarConFiltros(final TipoMovimiento tipoMovimiento, final UUID insumoId,
                                                       final LocalDate fecha, Pageable pageable) {

        Specification<MovimientoInventario> filtro = MovimientoInventarioSpecification.conFiltros(tipoMovimiento, insumoId, fecha);
        return movimientoInventarioRepository.findAll(filtro, pageable);
    }


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
