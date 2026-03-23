package com.sistema.Inventario.Services;

import com.sistema.Inventario.DTOs.ProductoDTO;
import com.sistema.Inventario.models.Producto;
import java.util.List;
import java.util.Optional;


public interface IProductoService {

    List<Producto> ListarProductosS();                   // METODO LISTAR PRODUCTOS
    Optional<Producto> ListarProductoPorIDS(Long id);        // METODO BUSCAR PRODUCTO POR ID
    Producto CrearProductosS(ProductoDTO dto);                    // METODO CREAR PRODUCTO
    Producto ActualizarProductosS(Long id, ProductoDTO dto);      // METODO ACTUALIZAR PRODUCTO
    void EliminarProductosS(Long id);                           // METODO ELIMINAR PRODUCTO

} 