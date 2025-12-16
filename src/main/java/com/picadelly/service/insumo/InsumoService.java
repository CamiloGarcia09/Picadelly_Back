package com.picadelly.service.insumo;

import com.picadelly.domain.insumo.Insumo;
import com.picadelly.domain.movimientoinventario.TipoMovimiento;
import com.picadelly.repository.insumo.InsumoRepository;
import com.picadelly.repository.insumo.filter.InsumoSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Transactional
@Service
public class InsumoService {

    private final InsumoRepository insumoRepository;

    public InsumoService(final InsumoRepository insumoRepository) {
        this.insumoRepository = insumoRepository;
    }

    public Page<Insumo> findAll(final Pageable pageable) {
        return insumoRepository.findAll(pageable);
    }

    public Page<Insumo> findByTipoInsumo (final String tipoInsumo, final Pageable pageable) {
        return insumoRepository.findByTipo_Nombre(tipoInsumo, pageable);
    }

    public Optional<Insumo> findById(final UUID id) {
        return insumoRepository.findById(id);
    }

    public Optional <Insumo> findByNombre(final String nombre) {
        return insumoRepository.findByNombre(nombre);
    }

    public Page<Insumo> buscarConFiltros(final UUID tipoInsumoID, final Pageable pageable) {

        Specification<Insumo> filtro = InsumoSpecification.conFiltros(tipoInsumoID);
        return insumoRepository.findAll(filtro, pageable);
    }


    public Insumo saveInsumo(final Insumo tipoInsumo) {
        return insumoRepository.save(tipoInsumo);
    }

    public void deleteInsumo(final UUID id) {
        insumoRepository.deleteById(id);
    }

    public Optional<Insumo> updateInsumo(UUID id, Insumo updated) {
        return insumoRepository.findById(id)
                .map(insumo -> {
                    insumo.setNombre(updated.getNombre());
                    insumo.setTipo(updated.getTipo());
                    insumo.setUnidadMedida(updated.getUnidadMedida());
                    insumo.setStockMinimo(updated.getStockMinimo());
                    insumo.setFechaCaducidad(updated.getFechaCaducidad());
                    insumo.setImagenURL(updated.getImagenURL());
                    return insumoRepository.save(insumo);
                });
    }
}
