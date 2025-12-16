package com.picadelly.repository.movimientoinventario;

import com.picadelly.domain.movimientoinventario.MovimientoInventario;
import com.picadelly.domain.movimientoinventario.TipoMovimiento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface MovimientoInventarioRepository extends JpaRepository<MovimientoInventario, UUID>,
        JpaSpecificationExecutor<MovimientoInventario> {

    Page<MovimientoInventario> findByMovimiento (final TipoMovimiento movimiento, final Pageable pageable);
    Page<MovimientoInventario> findByInsumo_Nombre (final String nombreInsumo, final Pageable pageable);

    @Query(value = """
SELECT * 
FROM movimientos_inventario m
WHERE DATE(m.fecha) = :fecha
""",
            countQuery = """
SELECT COUNT(*) 
FROM movimientos_inventario m
WHERE DATE(m.fecha) = :fecha
""",
            nativeQuery = true)
    Page<MovimientoInventario> findByFecha(@Param("fecha") final LocalDate fecha, final Pageable pageable);

}
