package com.ejemplo_semestral.principal.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ejemplo_semestral.principal.models.Usuario;
import com.ejemplo_semestral.principal.models.dto.UsuarioDto;
import com.ejemplo_semestral.principal.service.UserService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/usuarios")
public class UserController {

    @Autowired
    private UserService usuarioservice;

    @Operation(summary = "Este endpoint retorna un Hola")
    @GetMapping("/hola")
    public String Holamundo() {
        return "Hola mundo desde spring";
    }

    // CREATE
    @PostMapping
    public ResponseEntity<String> agregarUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioservice.agregarUsuario(usuario));
    }

    // READ ALL
    @GetMapping
    public List<Usuario> traerUsuarios() {
        return usuarioservice.obtenerUsuarios();
    }

    // READ ONE by correo
    @GetMapping("/correo/{correo}")
    public ResponseEntity<Usuario> traerUsuario(@PathVariable String correo) {
        Usuario usuario = usuarioservice.traerUsuario(correo);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        }
        return ResponseEntity.notFound().build();
    }

    // READ ONE by id (DTO)
    @GetMapping("/dto/{id}")
    public ResponseEntity<UsuarioDto> obtenerUsuarioId(@PathVariable int id) {
        UsuarioDto usuarioDto = usuarioservice.obtenerUsuarioId(id);
        if (usuarioDto != null) {
            return ResponseEntity.ok(usuarioDto);
        }
        return ResponseEntity.notFound().build();
    }

    // READ ONE by correo (DTO)
    @GetMapping("/dto/correo/{correo}")
    public ResponseEntity<UsuarioDto> obtenerUserDto(@PathVariable String correo) {
        return usuarioservice.obtenerUserDto(correo);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarUsuario(@PathVariable int id, @RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioservice.actualizarUsuario(id, usuario));
    }

    // DELETE by id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarUsuario(@PathVariable int id) {
        return ResponseEntity.ok(usuarioservice.borrarUsuario(id));
    }

    // DELETE by correo
    @DeleteMapping("/correo/{correo}")
    public ResponseEntity<String> borrarUsuarioPorCorreo(@PathVariable String correo) {
        return ResponseEntity.ok(usuarioservice.borrarUsuarioPorCorreo(correo));
    }
}