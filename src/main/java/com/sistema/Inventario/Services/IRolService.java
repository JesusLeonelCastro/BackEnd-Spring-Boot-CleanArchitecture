package com.sistema.Inventario.Services;

import com.sistema.Inventario.DTOs.RolDTO;
import com.sistema.Inventario.models.Rol;
import java.util.List;
import java.util.Optional;


public interface IRolService {

    List<Rol> ListarRolesS();             // METODO LISTAR ROLES
    Optional<Rol> BuscarRolPorIDS(Long id);   // METODO BUSCAR ROL POR ID
    Rol CrearRolesS(RolDTO dto);               // METODO CREAR ROL
    Rol ActualizarRolesS(Long id, RolDTO dto); // METODO ACTUALIZAR ROL
    void EliminarRolesS(Long id);              // METODO ELIMINAR ROL

} 