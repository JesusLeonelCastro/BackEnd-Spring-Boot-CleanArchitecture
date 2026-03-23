package com.sistema.Inventario.Repository;
import com.sistema.Inventario.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

// Repositorio para la entidad Producto, extiende JpaRepository para operaciones CRUD
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    
    Optional<Producto> findByCodigo(String codigo);
    Optional<Producto> findByNombre(String nombre);
    Optional<Producto> findByDescripcion(String descripcion);
    Optional<Producto> findByPrecioCompra(Double precioCompra);
    Optional<Producto> findByPrecioVenta(Double precioVenta);
    Optional<Producto> findByStock(Integer stock);
    Optional<Producto> findByStockMinimo(Integer stockMinimo);

    boolean existsByCodigo(String codigo);
    boolean existsByNombre(String nombre);
    boolean existsByDescripcion(String descripcion);
    boolean existsByPrecioCompra(Double precioCompra);
    boolean existsByPrecioVenta(Double precioVenta);
    boolean existsByStock(Integer stock);
    boolean existsByStockMinimo(Integer stockMinimo);
    
}

