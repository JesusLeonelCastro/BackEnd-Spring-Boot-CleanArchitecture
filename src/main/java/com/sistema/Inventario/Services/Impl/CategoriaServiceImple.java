package com.sistema.Inventario.Services.Impl;


import com.sistema.Inventario.DTOs.CategoriaDTO;
import com.sistema.Inventario.models.Categoria;
import com.sistema.Inventario.Repository.CategoriaRepository;
import com.sistema.Inventario.Services.ICategoriaService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImple implements ICategoriaService {

    private final CategoriaRepository categoriaRepository;
    

    //METODO LISTAR CATEGORIAS
    @Override // Implementación del método para obtener todas las categorias
    public List<Categoria> ListarCategoriasS() {
        return categoriaRepository.findAll();
    }
    
    //METODO BUSCAR CATEGORIA POR ID
    @Override 
    public Optional<Categoria> BuscarCategoriaPorIDS(Long id) {
        return categoriaRepository.findById(id);
    }

    //METODO CREAR CATEGORIA
    @Override 
    public Categoria CrearCategoriasS(CategoriaDTO dto) {
        // Verificar si el nombre de la categoria ya existe antes de crear una nueva categoria
        if (categoriaRepository.existsByNombre(dto.getNombre())) {
            throw new IllegalArgumentException("El nombre de la categoria ya existe"); 
        }
        Categoria categoria = new Categoria();
        // Asignar los valores del DTO al nuevo objeto Categoria
        categoria.setNombre(dto.getNombre());
        categoria.setDescripcion(dto.getDescripcion());
        return categoriaRepository.save(categoria);
    }

    //METODO ACTUALIZAR CATEGORIA
    @Override
    public Categoria ActualizarCategoriasS(Long id, CategoriaDTO dto) {
        Categoria categoria = categoriaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Categoria no encontrada con id: " + id));
        categoria.setNombre(dto.getNombre());
        categoria.setDescripcion(dto.getDescripcion());
        return categoriaRepository.save(categoria);
    }

    //METODO ELIMINAR CATEGORIA 
    @Override
    public void EliminarCategoriasS(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new RuntimeException("Categoria no encontrada con id: " + id);
        }
        categoriaRepository.deleteById(id);
    }
}
