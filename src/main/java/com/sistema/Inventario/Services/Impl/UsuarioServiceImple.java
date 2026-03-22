package com.sistema.Inventario.Services.Impl;

import com.sistema.Inventario.DTOs.UsuarioDTO;
import com.sistema.Inventario.models.Rol;
import com.sistema.Inventario.models.Usuario;
import com.sistema.Inventario.Repository.UsuarioRepository;
import com.sistema.Inventario.Repository.RolRepository;
import com.sistema.Inventario.Services.IUsuarioService;

import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImple implements IUsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;


    //METODO LISTAR USUARIOS
    @Override // Implementación del método para obtener todos los usuarios
    public List<Usuario> ListarUsuariosS() {
        return usuarioRepository.findAll();
    }

    //METODO BUSCAR USUARIO POR ID
    @Override 
    public Optional<Usuario> ListarUsuarioPorIDS(Long id) {
        return usuarioRepository.findById(id);
    }

    //METODO CREAR USUARIO
    @Override 
    public Usuario CrearUsuariosS(UsuarioDTO dto) {
        if (usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new IllegalArgumentException("El email del usuario ya existe"); 
        }

        //Join con Rol para asignar el rol al usuario
        Rol rol = rolRepository.findById(dto.getRolId())
            .orElseThrow(() -> new RuntimeException("Rol no encontrado con id: " + dto.getRolId()));
        /////////////////////////////////////////////////////////////////////////
    

        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());
        usuario.setPasswordHash(passwordEncoder.encode(dto.getPassword())); // Encriptar la contraseña antes de guardarla
        usuario.setRol(rol);
        return usuarioRepository.save(usuario);
    }

    //METODO ACTUALIZAR USUARIO
    @Override
    public Usuario ActualizarUsuariosS(Long id, UsuarioDTO dto) {

        //Join con Rol para asignar el rol al usuario
        Rol rol = rolRepository.findById(dto.getRolId())
            .orElseThrow(() -> new RuntimeException("Rol no encontrado con id: " + dto.getRolId()));
        /////////////////////////////////////////////////////////////////////////

        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));

        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());
        usuario.setPasswordHash(passwordEncoder.encode(dto.getPassword())); // Encriptar la contraseña antes de guardarla
        usuario.setRol(rol);
        return usuarioRepository.save(usuario);
    }

    //METODO ELIMINAR USUARIO
    @Override
    public void EliminarUsuariosS(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado con id: " + id);
        }
        usuarioRepository.deleteById(id);
    }
}
