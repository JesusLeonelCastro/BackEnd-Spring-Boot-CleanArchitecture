package com.sistema.Inventario.Repository;

import com.sistema.Inventario.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

// Repositorio para la entidad Categoria, extiende JpaRepository para operaciones CRUD
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    Optional<Categoria> findByNombre(String nombre);
    Optional<Categoria> findByDescripcion(String descripcion);

    boolean existsByNombre(String nombre);
    boolean existsByDescripcion(String descripcion);

}


