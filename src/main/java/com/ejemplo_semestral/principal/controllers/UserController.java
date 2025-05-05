package com.ejemplo_semestral.principal.controllers;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplo_semestral.principal.models.Usuario;
import com.ejemplo_semestral.principal.models.dto.UsuarioDto;
import com.ejemplo_semestral.principal.repository.UsuarioRepository;
import com.ejemplo_semestral.principal.service.UserService;

import io.swagger.v3.oas.annotations.Operation;


@RestController
public class UserController {

    private final UsuarioRepository usuarioRepository;
    @Autowired
    private UserService usuarioservice;
    UserService accionesUser = new UserService();

    UserController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    @Operation(summary = "Este endpoint retorna un Hola ")
    @GetMapping("/hola")
    public String Holamundo(){
        return "Hola mundo desde spring ";
    }


    @GetMapping("/usuarios")
    public List<Usuario> traerUsuarios(){
        return accionesUser.obtenerUsuarios();
    }
    
    @GetMapping("/usuarios/{correo}")
    public ResponseEntity<Usuario> traerUsuario(@PathVariable String correo){
        return ResponseEntity.ok(usuarioservice.traerUsuario(correo));
    }

    //@PostMapping("/usuarios")
    //public String agregarUsuario(@RequestBody Usuario usuario){
    //    return accionesUser.agregaUsuario(usuario);
    //}
    @PostMapping("/usuarios")
    public ResponseEntity<String> agregarUsuario (@RequestBody Usuario usuario){
        return ResponseEntity.ok(usuarioservice.agregaUsuario(usuario));

    }

    @DeleteMapping("/usuarios/{id}")
    public String borrarUsuario(@PathVariable int id ){
        return accionesUser.borrarUsuario(id);
    }

    @GetMapping("/usuariodto/{correo}")
    public ResponseEntity<UsuarioDto> obtenerUserDto(@PathVariable String correo){
        return usuarioservice.obtenerUserDto(correo);

    }
    @GetMapping("/usuariodtoid/{id}")
    public ResponseEntity<UsuarioDto> obtenerUsuarioId(@PathVariable int id) {
        UsuarioDto usuarioDto = usuarioservice.obtenerUsuarioId(id);
        if (usuarioDto != null) {
            return ResponseEntity.ok(usuarioDto); 
        }
        return ResponseEntity.notFound().build(); 
    }
}
