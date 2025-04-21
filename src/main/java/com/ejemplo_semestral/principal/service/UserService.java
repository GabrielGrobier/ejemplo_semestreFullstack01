package com.ejemplo_semestral.principal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejemplo_semestral.principal.models.Usuario;
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

    public String agregaUsuario(Usuario user){
        try{
            Boolean estado = usuarioRepository.existsByCorreo(user.getCorreo());
            if (estado == true){
                UsuarioEntity nuevoUsuario = new UsuarioEntity();
                nuevoUsuario.setId(user.getId());
                nuevoUsuario.setNombre(user.getNombre());
                nuevoUsuario.setApellido(user.getApellido());
                nuevoUsuario.setCorreo(user.getCorreo());
                return "Usuario Agregado correctamente ";
            }
            return "El usuario ya existe ";
        }
        catch(Exception e){
            return " ha ocurrido un error ";
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

}
