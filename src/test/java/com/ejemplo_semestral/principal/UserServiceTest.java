package com.ejemplo_semestral.principal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ejemplo_semestral.principal.models.Usuario;
import com.ejemplo_semestral.principal.models.entity.UsuarioEntity;
import com.ejemplo_semestral.principal.repository.UsuarioRepository;
import com.ejemplo_semestral.principal.service.UserService;

public class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }
        @Test
        //crea metodo para porbar un usuario 
        void traerUsuario_UsuarioExiste() {
            // Arrange o preparacion , se define un correo por el que se va a buscar 
            String correo = "pedro@gmail.com";
            // se crea un modelo entity simulado gracias a mock 
            UsuarioEntity mockEntity = new UsuarioEntity();
            mockEntity.setId(1);
            mockEntity.setNombre("Pedro");
            mockEntity.setApellido("Gonzalez");
            mockEntity.setCorreo(correo);

            //Simula el comportamiento de la accion finByCorreo  
            // lo que especifica que cuando se invoca el repository el debe retornar un entity de tipo Usuario 

            when(usuarioRepository.findByCorreo(correo)).thenReturn(mockEntity);

            // Act
            //llama al metodo bajo prueba 
            Usuario usuario = userService.traerUsuario(correo);

            // Assert
            //pruebas especificas de lo que se espera que pase con el metodo 
            // es espera que el usuario no sea nulll
            assertNotNull(usuario);
            // se espera que el usuario del id sea uno , con esto probamos el retorno de datos o pode obtener valor inyectado 
            assertEquals(1, usuario.getId());
            assertEquals("Pedro", usuario.getNombre());
            assertEquals("Gonzalez", usuario.getApellido());
            assertEquals(correo, usuario.getCorreo());
        }


        @Test
        void traerUsuario_UsuarioNoExiste() {
            // Arrange
            //se define el parametro o valores a utilizar 
            String correo = "no_existe@gmail.com";
            //se define el comportamiento , en este caso si no existe deberia retornar null
            when(usuarioRepository.findByCorreo(correo)).thenReturn(null);

            // Act
            //se pone a prueba el metodo
            Usuario usuario = userService.traerUsuario(correo);

            // Assert
            //se comprueba si el valor retornado es null 
            assertNull(usuario);
        }
}
