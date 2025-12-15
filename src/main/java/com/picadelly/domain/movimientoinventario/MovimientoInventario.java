package com.picadelly.domain.movimientoinventario;

import com.picadelly.domain.insumo.Insumo;
import com.picadelly.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;


@Getter
@Setter
@Entity
@Table(name = "movimientos_inventario")
public final class MovimientoInventario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "insumos_id", referencedColumnName = "id", nullable = false)
    private Insumo insumo;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_movimiento", nullable = false)
    private TipoMovimiento movimiento;

    @Column(name = "canitidad", nullable = false)
    private Float cantidad;

    @Column(name = "fecha", nullable = false)
    private Date fecha;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", nullable = false)
    private Usuario usuario;

    @Column(name = "detalle", nullable = false)
    private String detalle;

}
