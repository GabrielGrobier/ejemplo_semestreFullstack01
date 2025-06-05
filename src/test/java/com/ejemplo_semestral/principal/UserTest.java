package com.ejemplo_semestral.principal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ejemplo_semestral.principal.models.Usuario;
import com.ejemplo_semestral.principal.models.entity.UsuarioEntity;
import com.ejemplo_semestral.principal.repository.UsuarioRepository;
import com.ejemplo_semestral.principal.service.UserService;

public class UserTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UserService userService;

    private Usuario usuario;
    private UsuarioEntity usuarioEntity;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        usuario = new Usuario(1, "Juan", "Perez", "juan@gmail.com");
        usuarioEntity = new UsuarioEntity();
        usuarioEntity.setId(1);
        usuarioEntity.setNombre("juan");
        usuarioEntity.setApellido("Perez");
        usuarioEntity.setCorreo("juan@gmail.com");
    }

    @Test
    public void testAgregarUsuario_nuevo(){
        when(usuarioRepository.existsByCorreo(usuario.getCorreo())).thenReturn(false);
        when(usuarioRepository.save(any(UsuarioEntity.class))).thenReturn(usuarioEntity);

        String result = userService.agregarUsuario(usuario);
        assertEquals("Usuario agregado correctamente", result);

    }
    @Test
    public void testAgregarUsuario_existe(){
        when(usuarioRepository.existsByCorreo(usuario.getCorreo())).thenReturn(true);

        String result = userService.agregarUsuario(usuario);
        assertEquals("El usuario ya existe", result);
    }

    @Test
    public void tesTraerUsuarioporCorreo(){
        when(usuarioRepository.findByCorreo("juan@gmail.com")).thenReturn(usuarioEntity);
        Usuario result = userService.traerUsuario("juan@gmail.com");
        assertNotNull(result);
        assertEquals("juan", result.getNombre());


    }

    @Test
    public void testTraerUausrioNoExiste(){
        when(usuarioRepository.findByCorreo("noexiste@gmail.com")).thenReturn(null);
        Usuario result = userService.traerUsuario("noexiste@gmail.com");
        assertNull(result);
    }

    @Test
    public void actualizarUsuario_existe(){
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuarioEntity));
        when(usuarioRepository.save(any(UsuarioEntity.class))).thenReturn(usuarioEntity);

        Usuario nuevo =  new Usuario(1,"pedro","gomez","pedro@gmail.com");
        String result = userService.actualizarUsuario(1, nuevo);

        assertEquals("Usuario actualizado correctamente", result);
    }
    @Test
    public void borrarUsuario(){
        when(usuarioRepository.existsById(1)).thenReturn(true);
        doNothing().when(usuarioRepository).deleteById(1);
        String result = userService.borrarUsuario(1);

        assertEquals("Usuario borrado correctamente", result);
    }


    
}
