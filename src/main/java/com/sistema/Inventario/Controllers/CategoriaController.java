package com.sistema.Inventario.Controllers;
import com.sistema.Inventario.DTOs.CategoriaDTO;
import com.sistema.Inventario.models.Categoria;
import com.sistema.Inventario.Services.ICategoriaService;
import com.sistema.Inventario.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final ICategoriaService categoriaService;

    @GetMapping("/ListarCategorias")
    public ResponseEntity<List<Categoria>> listar() {
        List<Categoria> categorias = categoriaService.ListarCategoriasS();
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/BuscarCategoria/{id}")
    public ResponseEntity<ApiResponse<Categoria>> buscar(@PathVariable Long id) {
        return categoriaService.BuscarCategoriaPorIDS(id)
            .map(categoria -> ResponseEntity.ok(
                ApiResponse.success("Categoria encontrada", categoria)))
            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ApiResponse.error("Categoria no encontrada con id: " + id)));
    }

    @PostMapping("/CrearCategoria")
    public ResponseEntity<ApiResponse<Categoria>> crear(@Valid @RequestBody CategoriaDTO dto) {
        Categoria nueva = categoriaService.CrearCategoriasS(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(
            ApiResponse.success("Categoria creada correctamente", nueva)
        );
    }

    @PutMapping("/ActualizarCategoria/{id}")
    public ResponseEntity<ApiResponse<Categoria>> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody CategoriaDTO dto) {
        Categoria actualizado = categoriaService.ActualizarCategoriasS(id, dto);
        return ResponseEntity.ok(
            ApiResponse.success("Categoria actualizada correctamente", actualizado)
        );
    }

    @DeleteMapping("/EliminarCategoria/{id}")
    public ResponseEntity<ApiResponse<Void>> eliminar(@PathVariable Long id) {
        categoriaService.EliminarCategoriasS(id);
        return ResponseEntity.ok(
            ApiResponse.success("Categoria eliminada correctamente")
        );
    }
}