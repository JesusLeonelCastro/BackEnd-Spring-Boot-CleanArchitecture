package com.sistema.Inventario.Repository;
import com.sistema.Inventario.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

// Repositorio para la entidad Usuario, extiende JpaRepository para operaciones CRUD
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    Optional<Usuario> findByNombre(String nombre);
    Optional<Usuario> findByEmail(String email);

    boolean existsByNombre(String nombre);
    boolean existsByEmail(String email);
}

