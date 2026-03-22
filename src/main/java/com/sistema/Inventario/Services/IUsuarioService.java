package com.sistema.Inventario.Services;

import com.sistema.Inventario.DTOs.UsuarioDTO;
import com.sistema.Inventario.models.Usuario;
import java.util.List;
import java.util.Optional;


public interface IUsuarioService {

    List<Usuario> ListarUsuariosS();                   // METODO LISTAR USUARIOS
    Optional<Usuario> ListarUsuarioPorIDS(Long id);        // METODO BUSCAR USUARIO POR ID
    Usuario CrearUsuariosS(UsuarioDTO dto);                    // METODO CREAR USUARIO
    Usuario ActualizarUsuariosS(Long id, UsuarioDTO dto);      // METODO ACTUALIZAR USUARIO
    void EliminarUsuariosS(Long id);                           // METODO ELIMINAR USUARIO

} 