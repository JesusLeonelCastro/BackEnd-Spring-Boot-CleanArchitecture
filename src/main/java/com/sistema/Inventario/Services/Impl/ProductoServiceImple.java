package com.sistema.Inventario.Services.Impl;
import com.sistema.Inventario.DTOs.ProductoDTO;
import com.sistema.Inventario.models.Categoria;
import com.sistema.Inventario.models.Producto;
import com.sistema.Inventario.Repository.ProductoRepository;
import com.sistema.Inventario.Repository.CategoriaRepository;
import com.sistema.Inventario.Services.ICategoriaService;
import com.sistema.Inventario.Services.IProductoService;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductoServiceImple implements IProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;


    //METODO LISTAR PRODUCTOS
    @Override // Implementación del método para obtener todos los productos
    public List<Producto> ListarProductosS() {
        return productoRepository.findAll();
    }

    //METODO BUSCAR PRODUCTO POR ID
    @Override 
    public Optional<Producto> ListarProductoPorIDS(Long id) {
        return productoRepository.findById(id);
    }

    //METODO CREAR PRODUCTO
    @Override 
    public Producto CrearProductosS(ProductoDTO dto) {
        if (productoRepository.existsByCodigo(dto.getCodigo())) {
            throw new IllegalArgumentException("El código del producto ya existe"); 
        }

        //Join con Categoria para asignar la categoría al producto
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
            .orElseThrow(() -> new RuntimeException("Categoría no encontrada con id: " + dto.getCategoriaId()));
        /////////////////////////////////////////////////////////////////////////
    
        Producto producto = new Producto();
        producto.setCategoria(categoria);   
        producto.setCodigo(dto.getCodigo());
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecioCompra(dto.getPrecioCompra());
        producto.setPrecioVenta(dto.getPrecioVenta());
        producto.setStock(dto.getStock());
        producto.setStockMinimo(dto.getStockMinimo());
        return productoRepository.save(producto);
    }

    //METODO ACTUALIZAR PRODUCTO
    @Override
    public Producto ActualizarProductosS(Long id, ProductoDTO dto) {

        //Join con Categoria para asignar la categoría al producto
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
            .orElseThrow(() -> new RuntimeException("Categoría no encontrada con id: " + dto.getCategoriaId()));
        /////////////////////////////////////////////////////////////////////////

        Producto producto = productoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));

        producto.setCategoria(categoria);
        producto.setCodigo(dto.getCodigo());
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecioCompra(dto.getPrecioCompra());
        producto.setPrecioVenta(dto.getPrecioVenta());
        producto.setStock(dto.getStock());
        producto.setStockMinimo(dto.getStockMinimo());
        return productoRepository.save(producto);
    }

    //METODO ELIMINAR PRODUCTO
    @Override
    public void EliminarProductosS(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado con id: " + id);
        }
        productoRepository.deleteById(id);
    }
}
