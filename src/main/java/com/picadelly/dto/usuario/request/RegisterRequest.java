package com.picadelly.dto.usuario.request;

import com.picadelly.domain.usuario.Rol;
import lombok.Data;

@Data
public class RegisterRequest {
    private String nombre;
    private String documento;
    private String email;
    private Rol rol;
    private String password;

}
