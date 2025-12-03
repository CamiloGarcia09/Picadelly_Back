package com.picadelly.service.usuario;

import com.picadelly.domain.usuario.Rol;
import com.picadelly.domain.usuario.Usuario;
import com.picadelly.dto.usuario.request.UserUpdateRequest;
import com.picadelly.dto.usuario.response.UserResponse;
import com.picadelly.repository.usuario.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UsuarioRepository usuarioRepository;

    public List<UserResponse> listUsers() {
        return usuarioRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public UserResponse getUserById(UUID id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Usuario no encontrado"));
        return toResponse(usuario);
    }

    // Editar usuario
    public UserResponse updateUser(UUID id, UserUpdateRequest req) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Usuario no encontrado"));

        if (req.getNombre() != null) usuario.setNombre(req.getNombre());
        if (req.getEstado() != null) usuario.setEstado(req.getEstado());

        if (req.getRol() != null) {
            try {
                usuario.setRol(Rol.valueOf(req.getRol()));
            } catch (Exception e) {
                throw new IllegalArgumentException("Rol inválido");
            }
        }

        usuarioRepository.save(usuario);
        return toResponse(usuario);
    }

    // Convertir a DTO
    private UserResponse toResponse(Usuario usuario) {
        UserResponse r = new UserResponse();
        r.setId(usuario.getId().toString());
        r.setNombre(usuario.getNombre());
        r.setDocumento(usuario.getDocumento());
        r.setEmail(usuario.getEmail());
        r.setRol(usuario.getRol().name());
        r.setEstado(usuario.isEstado());
        return r;
    }
}
