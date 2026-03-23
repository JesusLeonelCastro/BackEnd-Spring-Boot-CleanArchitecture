package com.sistema.Inventario.Controllers;

import com.sistema.Inventario.DTOs.RolDTO;
import com.sistema.Inventario.models.Rol;
import com.sistema.Inventario.Services.IRolService;
import com.sistema.Inventario.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RolController {

    private final IRolService rolService;

    @GetMapping("/ListarRoles")
    public ResponseEntity<List<Rol>> listar() {
        List<Rol> roles = rolService.ListarRolesS();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/BuscarRol/{id}")
    public ResponseEntity<ApiResponse<Rol>> buscar(@PathVariable Long id) {
        return rolService.BuscarRolPorIDS(id)
            .map(rol -> ResponseEntity.ok(
                ApiResponse.success("Rol encontrado", rol)))
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ApiResponse.error("Rol no encontrado con id: " + id)));
    }

    @PostMapping("/CrearRol")
    public ResponseEntity<ApiResponse<Rol>> crear(@Valid @RequestBody RolDTO dto) {
        Rol nuevo = rolService.CrearRolesS(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(
            ApiResponse.success("Rol creado correctamente", nuevo)
        );
    }

    @PutMapping("/ActualizarRol/{id}")
    public ResponseEntity<ApiResponse<Rol>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody RolDTO dto) {
        Rol actualizado = rolService.ActualizarRolesS(id, dto);
        return ResponseEntity.ok(
            ApiResponse.success("Rol actualizado correctamente", actualizado)
        );
    }

    @DeleteMapping("/EliminarRol/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        rolService.EliminarRolesS(id);
        return ResponseEntity.ok(
            ApiResponse.success("Rol eliminado correctamente")
        );
    }
}