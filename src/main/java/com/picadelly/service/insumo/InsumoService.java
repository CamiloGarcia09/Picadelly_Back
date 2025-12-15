package com.picadelly.service.insumo;

import com.picadelly.domain.insumo.Insumo;
import com.picadelly.repository.insumo.InsumoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional
@Service
public class InsumoService {

    private final InsumoRepository insumoRepository;

    public InsumoService(final InsumoRepository insumoRepository) {
        this.insumoRepository = insumoRepository;
    }

    public List<Insumo> findAll() {
        return insumoRepository.findAll();
    }

    public Optional<Insumo> findById(final UUID id) {
        return insumoRepository.findById(id);
    }

    public Optional <Insumo> findByNombre(final String nombre) {
        return insumoRepository.findByNombre(nombre);
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
