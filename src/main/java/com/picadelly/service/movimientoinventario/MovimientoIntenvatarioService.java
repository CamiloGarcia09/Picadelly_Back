package com.picadelly.service.movimientoinventario;

import com.picadelly.domain.movimientoinventario.MovimientoInventario;
import com.picadelly.repository.movimientoinventario.MovimientoInventarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional
@Service
public class MovimientoIntenvatarioService {

    private final MovimientoInventarioRepository movimientoInventarioRepository;

    public MovimientoIntenvatarioService(final MovimientoInventarioRepository movimientoInventarioRepository) {
        this.movimientoInventarioRepository = movimientoInventarioRepository;
    }

    public List<MovimientoInventario> findAll() {
        return movimientoInventarioRepository.findAll();
    }

    public Optional<MovimientoInventario> findById(final UUID id) {
        return movimientoInventarioRepository.findById(id);
    }


    public MovimientoInventario saveMovimientoInventario(final MovimientoInventario tipoInsumo) {
        return movimientoInventarioRepository.save(tipoInsumo);
    }


}
