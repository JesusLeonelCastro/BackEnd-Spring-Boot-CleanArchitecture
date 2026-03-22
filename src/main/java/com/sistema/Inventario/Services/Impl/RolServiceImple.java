package com.sistema.Inventario.Services.Impl;


import com.sistema.Inventario.DTOs.RolDTO;
import com.sistema.Inventario.models.Rol;
import com.sistema.Inventario.Repository.RolRepository;
import com.sistema.Inventario.Services.IRolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RolServiceImple implements IRolService {

    private final RolRepository rolRepository;
    

    //METODO LISTAR ROLES
    @Override // Implementación del método para obtener todos los roles
    public List<Rol> ListarRolesS() {
        return rolRepository.findAll();
    }
    
    //METODO BUSCAR ROL POR ID
    @Override 
    public Optional<Rol> BuscarRolPorIDS(Long id) {
        return rolRepository.findById(id);
    }

    //METODO CREAR ROL
    @Override 
    public Rol CrearRolesS(RolDTO dto) {
        if (rolRepository.existsByNombre(dto.getNombre())) {
            throw new IllegalArgumentException("El nombre del rol ya existe"); 
        }
        Rol rol = new Rol();
        // Asignar los valores del DTO al nuevo objeto Rol
        rol.setNombre(dto.getNombre());
        rol.setDescripcion(dto.getDescripcion());
        return rolRepository.save(rol);
    }

    //METODO ACTUALIZAR ROL
    @Override
    public Rol ActualizarRolesS(Long id, RolDTO dto) {
        Rol rol = rolRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Rol no encontrado con id: " + id));
        rol.setNombre(dto.getNombre());
        rol.setDescripcion(dto.getDescripcion());
        return rolRepository.save(rol);
    }

    //METODO ELIMINAR ROL
    @Override
    public void EliminarRolesS(Long id) {
        if (!rolRepository.existsById(id)) {
            throw new RuntimeException("Rol no encontrado con id: " + id);
        }
        rolRepository.deleteById(id);
    }
}
