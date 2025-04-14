package com.ejemplo_semestral.principal.controllers;



import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplo_semestral.principal.models.Usuario;
import com.ejemplo_semestral.principal.service.UserService;


@RestController
public class UserController {
    UserService accionesUser = new UserService();

    @GetMapping("/hola")
    public String Holamundo(){
        return "Hola mundo desde spring ";
    }


    @GetMapping("/usuarios")
    public List<Usuario> traerUsuarios(){
        return accionesUser.obtenerUsuarios();
    }
    
    @GetMapping("/usuarios/{id}")
    public Usuario traerUsuario(@PathVariable int id){
        return accionesUser.traerUsuario(id);
    }

    @PostMapping("/usuarios")
    public String agregarUsuario(@RequestBody Usuario usuario){
        return accionesUser.agregaUsuario(usuario);
    }

    @DeleteMapping("/usuarios/{id}")
    public String borrarUsuario(@PathVariable int id ){
        return accionesUser.borrarUsuario(id);
    }
}
