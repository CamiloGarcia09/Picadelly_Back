package com.picadelly.service.tipoinsumo;

import com.picadelly.domain.tipoinsumo.TipoInsumo;
import com.picadelly.repository.tipoinsumo.TipoInsumoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional
@Service
public class TipoInsumoService {

    private final TipoInsumoRepository tipoInsumoRepository;

    public TipoInsumoService(final TipoInsumoRepository tipoInsumoRepository) {
        this.tipoInsumoRepository = tipoInsumoRepository;
    }

    public List<TipoInsumo> findAll() {
        return tipoInsumoRepository.findAll();
    }

    public Optional<TipoInsumo> findById(final UUID id) {
        return tipoInsumoRepository.findById(id);
    }

    public Optional <TipoInsumo> findByNombre(final String nombre) {
        return tipoInsumoRepository.findByNombre(nombre);
    }


    public TipoInsumo saveTipoInsumo(final TipoInsumo tipoInsumo) {
        return tipoInsumoRepository.save(tipoInsumo);
    }


    public void deleteTipoInsumo(final UUID id) {
        tipoInsumoRepository.deleteById(id);
    }


    public Optional<TipoInsumo> updateTipoInsumo(UUID id, TipoInsumo updated) {
        return tipoInsumoRepository.findById(id)
                .map(tipoInsumo -> {
                    tipoInsumo.setNombre(updated.getNombre());
                    return tipoInsumoRepository.save(tipoInsumo);
                });
    }

}
