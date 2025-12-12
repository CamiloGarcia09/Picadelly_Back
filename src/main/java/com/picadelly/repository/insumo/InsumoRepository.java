package com.picadelly.repository.insumo;

import com.picadelly.domain.insumo.Insumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface InsumoRepository extends JpaRepository<Insumo, UUID> {
    Optional<Insumo> findById(UUID id);
    Optional<Insumo> findByNombre(String nombre);
}
