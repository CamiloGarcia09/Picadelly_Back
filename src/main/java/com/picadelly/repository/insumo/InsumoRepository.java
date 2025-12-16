package com.picadelly.repository.insumo;

import com.picadelly.domain.insumo.Insumo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface InsumoRepository extends JpaRepository<Insumo, UUID> {

    Optional<Insumo> findById(final UUID id);
    Optional<Insumo> findByNombre(final String nombre);

    Page<Insumo> findByTipo_Nombre (final String movimiento, final Pageable pageable);
}
