package com.sistema.Inventario.Services;

import com.sistema.Inventario.DTOs.RolDTO;
import com.sistema.Inventario.models.Rol;
import java.util.List;
import java.util.Optional;


public interface IRolService {

    List<Rol> getAllRoles();             // METODO LISTAR ROLES
    Optional<Rol> getRolById(Long id);   // METODO BUSCAR ROL POR ID
    Rol crear(RolDTO dto);               // METODO CREAR ROL
    Rol actualizar(Long id, RolDTO dto); // METODO ACTUALIZAR ROL
    void eliminar(Long id);              // METODO ELIMINAR ROL

} 