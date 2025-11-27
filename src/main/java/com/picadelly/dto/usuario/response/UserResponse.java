package com.picadelly.dto.usuario.response;

import lombok.Data;

@Data
public class UserResponse {
    private String id;
    private String nombre;
    private String documento;
    private String email;
    private String rol;
    private boolean estado;
}