package com.picadelly.dto.usuario.request;

import lombok.Data;

@Data
public class RegisterRequest {
    private String nombre;
    private String documento;
    private String email;
    private String password;
}
