package com.picadelly.repository.movimientoinventario.filter;

import com.picadelly.domain.movimientoinventario.MovimientoInventario;
import com.picadelly.domain.movimientoinventario.TipoMovimiento;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class MovimientoInventarioSpecification {

    public static Specification<MovimientoInventario> conFiltros(TipoMovimiento tipoMovimiento, UUID insumoId, LocalDate fecha) {
        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (tipoMovimiento != null) {
                predicates.add(cb.equal(root.get("movimiento"), tipoMovimiento));
            }

            // Filtro por insumo (FK: insumos_id)
            if (insumoId != null) {
                predicates.add(cb.equal(root.get("insumo").get("id"), insumoId));
            }

            // Filtro por fecha (opcional)
            if (fecha != null) {

                LocalDateTime inicioDia = fecha.atStartOfDay();
                LocalDateTime finDia = fecha.atTime(LocalTime.MAX);

                Date fechaInicio = Date.from(inicioDia.atZone(ZoneId.systemDefault()).toInstant());
                Date fechaFin = Date.from(finDia.atZone(ZoneId.systemDefault()).toInstant());

                predicates.add(cb.between(root.get("fecha"), fechaInicio, fechaFin));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}