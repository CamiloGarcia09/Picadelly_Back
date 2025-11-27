package com.picadelly.domain.usuario;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "documento", nullable = false, length = 20, unique = true)
    private String documento;

    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol", nullable = false)
    private Rol rol;

    @Column(name = "estado", nullable = false)
    private boolean estado = true;
}
