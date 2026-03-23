package com.sistema.Inventario.Services;

import com.sistema.Inventario.DTOs.CategoriaDTO;
import com.sistema.Inventario.models.Categoria;

import java.util.List;
import java.util.Optional;


public interface ICategoriaService {

    List<Categoria> ListarCategoriasS();                          // METODO LISTAR CATEGORIAS
    Optional<Categoria> BuscarCategoriaPorIDS(Long id);          // METODO BUSCAR CATEGORIA POR ID
    Categoria CrearCategoriasS(CategoriaDTO dto);               // METODO CREAR CATEGORIA
    Categoria ActualizarCategoriasS(Long id, CategoriaDTO dto); // METODO ACTUALIZAR CATEGORIA
    void EliminarCategoriasS(Long id);                          // METODO ELIMINAR CATEGORIA

} 