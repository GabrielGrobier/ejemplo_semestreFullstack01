package com.ejemplo_semestral.principal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.ejemplo_semestral.principal.models.Usuario;
import com.ejemplo_semestral.principal.models.dto.UsuarioDto;
import com.ejemplo_semestral.principal.models.entity.UsuarioEntity;
import com.ejemplo_semestral.principal.repository.UsuarioRepository;

@Service
public class UserService {
    // conectando el service con el repository correspondiente 
    @Autowired
    private UsuarioRepository usuarioRepository;

    private final List<Usuario> usuarios = new ArrayList<>();

    public UserService(){
        usuarios.add(new Usuario(1,"Pedro", "gonzalez", "pedro@gmail.com"));
    }

    public List<Usuario> obtenerUsuarios(){
        return usuarios;
    }

    public Usuario traerUsuario(String correo){
        try{
            UsuarioEntity usuario = usuarioRepository.findByCorreo(correo);
            if (usuario!=null){
                Usuario usuarioNuevo = new Usuario(
                    usuario.getId(),
                    usuario.getNombre(),
                    usuario.getApellido(),
                    usuario.getCorreo()
                );
                return usuarioNuevo;

            }
            return null;


        }
        catch (Exception e){
            return null;
        }
        

    }

public String agregaUsuario(Usuario user) {
    try {
        boolean estado = usuarioRepository.existsByCorreo(user.getCorreo());
        if (!estado) {
            UsuarioEntity nuevoUsuario = new UsuarioEntity();
            nuevoUsuario.setNombre(user.getNombre());
            nuevoUsuario.setApellido(user.getApellido());
            nuevoUsuario.setCorreo(user.getCorreo());
            usuarioRepository.save(nuevoUsuario);
            return "Usuario agregado correctamente";
        }
        return "El usuario ya existe";
    } catch (ObjectOptimisticLockingFailureException e) {
        return "Error de concurrencia: " + e.getMessage();
    } catch (Exception e) {
        return "Ha ocurrido un error: " + e.getMessage();
    }
}


    public String borrarUsuario (int id ){
        for (Usuario user : usuarios){
            if(user.getId()== id ){
                usuarios.remove(user);
                return "usuario borrado correctamente ";
            }
        }
        return null;
    }

    public ResponseEntity<UsuarioDto> obtenerUserDto( String correo){
        Boolean estado = usuarioRepository.existsByCorreo(correo);
        if (estado){
            UsuarioEntity nuevoUsuario = usuarioRepository.findByCorreo(correo);
            UsuarioDto usuarioResponse = new UsuarioDto(
                nuevoUsuario.getNombre(),
                nuevoUsuario.getCorreo()
            );
            return ResponseEntity.ok(usuarioResponse);


        }
        return ResponseEntity.notFound().build();

    }

    public UsuarioDto obtenerUsuarioId(int id){
        try{
            Boolean estado = usuarioRepository.existsById(id);
            if (estado){
                UsuarioEntity nuevoUsuario = usuarioRepository.findUsuarioById(id);
                UsuarioDto responseUsuario = new UsuarioDto(
                    nuevoUsuario.getNombre(),
                    nuevoUsuario.getCorreo()
                );
                return responseUsuario;
            }
            return null ;
        }
        catch(Exception e){
            
            return null;
        }


    }

}
