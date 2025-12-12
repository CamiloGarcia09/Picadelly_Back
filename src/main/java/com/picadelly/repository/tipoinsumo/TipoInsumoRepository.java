package com.picadelly.repository.tipoinsumo;

import com.picadelly.domain.tipoinsumo.TipoInsumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TipoInsumoRepository extends JpaRepository<TipoInsumo, UUID> {

    Optional<TipoInsumo> findById(UUID id);
    Optional<TipoInsumo> findByNombre(String nombre);
}
