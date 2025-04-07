package com.ejemplo_semestral.principal.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplo_semestral.principal.models.Usuario;

@RestController
public class UserController {
    @GetMapping("/hola")
    public String Holamundo(){
        return "Hola mundo desde spring ";
    }

    @GetMapping("/usuarios")
    public List<Usuario> usuarios(){
        List<Usuario> listUser = new ArrayList<>();
        Usuario user1 = new Usuario("pedro", "gonazalez", "pedro@gmail.com");
        listUser.add(user1);
        return listUser;

    }

}
