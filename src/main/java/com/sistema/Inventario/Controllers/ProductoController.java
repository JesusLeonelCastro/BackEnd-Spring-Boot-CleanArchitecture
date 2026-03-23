package com.sistema.Inventario.Controllers;

import com.sistema.Inventario.DTOs.ProductoDTO;
import com.sistema.Inventario.models.Producto;
import com.sistema.Inventario.Services.IProductoService;

import com.sistema.Inventario.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final IProductoService productoService;

    @GetMapping("/ListarProducto")
    public ResponseEntity<List<Producto>> listar() {
        List<Producto> productos = productoService.ListarProductosS();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/BuscarProducto/{id}")
    public ResponseEntity<ApiResponse<Producto>> buscar(@PathVariable Long id) {
        return productoService.ListarProductoPorIDS(id)
            .map(producto -> ResponseEntity.ok(
                ApiResponse.success("Producto encontrado", producto)))
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ApiResponse.error("Producto no encontrado con id: " + id)));
    }

    @PostMapping("/CrearProducto")
    public ResponseEntity<ApiResponse<Producto>> crear(@Valid @RequestBody ProductoDTO dto) {
        Producto nuevo = productoService.CrearProductosS(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(
            ApiResponse.success("Producto creado correctamente", nuevo)
        );
    }

    @PutMapping("/ActualizarProducto/{id}")
    public ResponseEntity<ApiResponse<Producto>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody ProductoDTO dto) {
        Producto actualizado = productoService.ActualizarProductosS(id, dto);
        return ResponseEntity.ok(
            ApiResponse.success("Producto actualizado correctamente", actualizado)
        );
    }

    @DeleteMapping("/EliminarProducto/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        productoService.EliminarProductosS(id);
        return ResponseEntity.ok(
            ApiResponse.success("Producto eliminado correctamente")
        );
    }
}