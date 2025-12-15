package com.picadelly.domain.insumo;

import com.picadelly.domain.tipoinsumo.TipoInsumo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;


@Getter
@Setter
@Entity
@Table(name = "insumos")
public final    class Insumo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tipos_insumos_id", referencedColumnName = "id", nullable = false)
    private TipoInsumo tipo;

    @Enumerated(EnumType.STRING)
    @Column(name = "unidad_medida", nullable = false)
    private UnidadMedida unidadMedida;

    @Column(name = "stock_minimo", nullable = false)
    private Float stockMinimo;

    @Column(name = "stock_actual", nullable = false)
    private Float stockActual;

    @Column(name = "fecha_caducidad")
    private Date fechaCaducidad;

    @Column(name = "imagen_url")
    private String imagenURL;


}
