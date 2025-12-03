package com.picadelly.controller.usuario;

import com.picadelly.dto.ApiResponse;
import com.picadelly.dto.usuario.request.UserUpdateRequest;
import com.picadelly.dto.usuario.response.UserResponse;
import com.picadelly.service.usuario.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<List<UserResponse>> listUsers() {
        return ApiResponse.<List<UserResponse>>builder()
                .success(true)
                .message("Lista de usuarios")
                .data(userService.listUsers())
                .build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<UserResponse> getUser(@PathVariable UUID id) {
        return ApiResponse.<UserResponse>builder()
                .success(true)
                .message("Usuario encontrado")
                .data(userService.getUserById(id))
                .build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<UserResponse> updateUser(
            @PathVariable UUID id,
            @RequestBody UserUpdateRequest request
    ) {
        return ApiResponse.<UserResponse>builder()
                .success(true)
                .message("Usuario actualizado correctamente")
                .data(userService.updateUser(id, request))
                .build();
    }
}
