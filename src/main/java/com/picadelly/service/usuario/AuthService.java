package com.picadelly.service.usuario;

import com.picadelly.domain.usuario.Rol;

import com.picadelly.domain.usuario.Usuario;
import com.picadelly.dto.usuario.request.AuthRequest;
import com.picadelly.dto.usuario.request.RegisterRequest;
import com.picadelly.dto.usuario.response.AuthResponse;
import com.picadelly.dto.usuario.response.UserResponse;
import com.picadelly.repository.usuario.UsuarioRepository;
import com.picadelly.service.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse register(RegisterRequest request) {

        if (usuarioRepository.findByEmail(request.getEmail()).isPresent() ||
                usuarioRepository.findByDocumento(request.getDocumento()).isPresent()) {
            throw new IllegalStateException("No se pudo completar el registro");
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setDocumento(request.getDocumento());
        usuario.setEmail(request.getEmail());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setRol(request.getRol());
        usuario.setEstado(true);

        usuarioRepository.save(usuario);

        String token = jwtService.generateToken(usuario.getEmail(), usuario.getRol().name());
        return new AuthResponse(token);
    }

    public AuthResponse login(AuthRequest request) {
        String userInput = request.getUsername();

        Usuario usuario = usuarioRepository.findByDocumento(userInput)
                .or(() -> usuarioRepository.findByEmail(userInput))
                .orElseThrow(() -> new BadCredentialsException("Credenciales inválidas"));

        if (!usuario.isEstado()) {
            throw new BadCredentialsException("Credenciales inválidas");
        }

        if (!passwordEncoder.matches(request.getPassword(), usuario.getPassword())) {
            throw new BadCredentialsException("Credenciales inválidas");
        }

        String token = jwtService.generateToken(usuario.getEmail(), usuario.getRol().name());
        return new AuthResponse(token);
    }

    public UserResponse me(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new AccessDeniedException("No autorizado"));

        if (!usuario.isEstado()) {
            throw new AccessDeniedException("No autorizado");
        }

        UserResponse response = new UserResponse();
        response.setId(usuario.getId().toString());
        response.setNombre(usuario.getNombre());
        response.setDocumento(usuario.getDocumento());
        response.setEmail(usuario.getEmail());
        response.setRol(usuario.getRol().name());
        response.setEstado(usuario.isEstado());

        return response;
    }

}
