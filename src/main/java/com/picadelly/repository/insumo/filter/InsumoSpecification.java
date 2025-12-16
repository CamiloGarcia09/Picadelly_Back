package com.picadelly.repository.insumo.filter;

import com.picadelly.domain.insumo.Insumo;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InsumoSpecification {

    public static Specification<Insumo> conFiltros(UUID tipoInsumoID) {
        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (tipoInsumoID != null) {
                predicates.add(cb.equal(root.get("tipo").get("id"), tipoInsumoID));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}