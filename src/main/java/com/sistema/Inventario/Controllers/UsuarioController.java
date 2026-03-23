package com.sistema.Inventario.Controllers;

import com.sistema.Inventario.DTOs.UsuarioDTO;
import com.sistema.Inventario.models.Usuario;
import com.sistema.Inventario.Services.IUsuarioService;

import com.sistema.Inventario.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final IUsuarioService usuarioService;

    @GetMapping("/ListarUsuario")
    public ResponseEntity<List<Usuario>> listar() {
        List<Usuario> usuarios = usuarioService.ListarUsuariosS();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/BuscarUsuario/{id}")
    public ResponseEntity<ApiResponse<Usuario>> buscar(@PathVariable Long id) {
        return usuarioService.ListarUsuarioPorIDS(id)
            .map(usuario -> ResponseEntity.ok(
                ApiResponse.success("Usuario encontrado", usuario)))
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ApiResponse.error("Usuario no encontrado con id: " + id)));
    }

    @PostMapping("/CrearUsuario")
    public ResponseEntity<ApiResponse<Usuario>> crear(@Valid @RequestBody UsuarioDTO dto) {
        Usuario nuevo = usuarioService.CrearUsuariosS(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(
            ApiResponse.success("Usuario creado correctamente", nuevo)
        );
    }

    @PutMapping("/ActualizarUsuario/{id}")
    public ResponseEntity<ApiResponse<Usuario>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioDTO dto) {
        Usuario actualizado = usuarioService.ActualizarUsuariosS(id, dto);
        return ResponseEntity.ok(
            ApiResponse.success("Usuario actualizado correctamente", actualizado)
        );
    }

    @DeleteMapping("/EliminarUsuario/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        usuarioService.EliminarUsuariosS(id);
        return ResponseEntity.ok(
            ApiResponse.success("Usuario eliminado correctamente")
        );
    }
}