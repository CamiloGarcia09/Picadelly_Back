package com.picadelly.controller.usuario;

import com.picadelly.dto.ApiResponse;
import com.picadelly.dto.usuario.request.AuthRequest;
import com.picadelly.dto.usuario.request.RegisterRequest;
import com.picadelly.dto.usuario.response.AuthResponse;
import com.picadelly.dto.usuario.response.UserResponse;
import com.picadelly.service.jwt.JwtService;
import com.picadelly.service.usuario.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ApiResponse<Void> register(@RequestBody RegisterRequest request) {
        authService.register(request);
        return ApiResponse.<Void>builder()
                .success(true)
                .message("Usuario registrado correctamente")
                .data(null)
                .build();
    }

    @PostMapping("/login")
    public ApiResponse<AuthResponse> login(@RequestBody AuthRequest request) {
        AuthResponse response = authService.login(request);
        return ApiResponse.<AuthResponse>builder()
                .success(true)
                .message("Inicio de sesión exitoso")
                .data(response)
                .build();
    }

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ApiResponse<UserResponse> me(@RequestHeader("Authorization") String header) {
        String token = header.substring(7);
        String email = jwtService.extractUsername(token);
        UserResponse user = authService.me(email);
        return ApiResponse.<UserResponse>builder()
                .success(true)
                .message("Usuario")
                .data(user)
                .build();
    }
}
