package com.picadelly.dto.usuario.request;

import lombok.Data;

@Data
public class UserUpdateRequest {
    private String nombre;
    private String email;
    private Boolean estado;
    private String rol;
}