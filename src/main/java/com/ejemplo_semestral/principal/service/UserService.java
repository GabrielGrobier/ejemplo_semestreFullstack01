package com.ejemplo_semestral.principal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ejemplo_semestral.principal.models.Usuario;

@Service
public class UserService {

    private final List<Usuario> usuarios = new ArrayList<>();

    public UserService(){
        usuarios.add(new Usuario(1,"Pedro", "gonzalez", "pedro@gmail.com"));
    }

    public List<Usuario> obtenerUsuarios(){
        return usuarios;
    }

    public Usuario traerUsuario(int id){
        for (Usuario user : usuarios){
            if(user.getId() == id){
                return user;
            }
        }
        return null;

    }

    public String agregaUsuario(Usuario user){
        usuarios.add(user);
        return "Usuario agregado correctamente ";

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
