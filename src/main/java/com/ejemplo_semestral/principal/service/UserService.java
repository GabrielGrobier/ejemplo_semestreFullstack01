package com.ejemplo_semestral.principal.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;

import com.ejemplo_semestral.principal.models.Usuario;
import com.ejemplo_semestral.principal.models.dto.UsuarioDto;
import com.ejemplo_semestral.principal.models.entity.UsuarioEntity;
import com.ejemplo_semestral.principal.repository.UsuarioRepository;

@Service
public class UserService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    // CREATE
    public String agregarUsuario(Usuario user) {
        try {
            boolean existe = usuarioRepository.existsByCorreo(user.getCorreo());
            if (!existe) {
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

    // READ ALL
    public List<Usuario> obtenerUsuarios() {
        List<UsuarioEntity> entidades = usuarioRepository.findAll();
        return entidades.stream()
                .map(u -> new Usuario(u.getId(), u.getNombre(), u.getApellido(), u.getCorreo()))
                .collect(Collectors.toList());
    }

    // READ ONE by correo
    public Usuario traerUsuario(String correo) {
        try {
            UsuarioEntity usuario = usuarioRepository.findByCorreo(correo);
            if (usuario != null) {
                return new Usuario(
                        usuario.getId(),
                        usuario.getNombre(),
                        usuario.getApellido(),
                        usuario.getCorreo()
                );
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    // READ ONE by id
    public UsuarioDto obtenerUsuarioId(int id) {
        try {
            Optional<UsuarioEntity> usuarioOpt = usuarioRepository.findById(id);
            if (usuarioOpt.isPresent()) {
                UsuarioEntity usuario = usuarioOpt.get();
                return new UsuarioDto(usuario.getNombre(), usuario.getCorreo());
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    // UPDATE
    public String actualizarUsuario(int id, Usuario user) {
        try {
            Optional<UsuarioEntity> usuarioOpt = usuarioRepository.findById(id);
            if (usuarioOpt.isPresent()) {
                UsuarioEntity usuario = usuarioOpt.get();
                usuario.setNombre(user.getNombre());
                usuario.setApellido(user.getApellido());
                usuario.setCorreo(user.getCorreo());
                usuarioRepository.save(usuario);
                return "Usuario actualizado correctamente";
            }
            return "Usuario no encontrado";
        } catch (Exception e) {
            return "Error al actualizar usuario: " + e.getMessage();
        }
    }

    // DELETE by id
    public String borrarUsuario(int id) {
        try {
            if (usuarioRepository.existsById(id)) {
                usuarioRepository.deleteById(id);
                return "Usuario borrado correctamente";
            }
            return "Usuario no encontrado";
        } catch (Exception e) {
            return "Error al borrar usuario: " + e.getMessage();
        }
    }

    // DELETE by correo
    public String borrarUsuarioPorCorreo(String correo) {
        try {
            if (usuarioRepository.existsByCorreo(correo)) {
                usuarioRepository.deleteByCorreo(correo);
                return "Usuario borrado correctamente";
            }
            return "Usuario no encontrado";
        } catch (Exception e) {
            return "Error al borrar usuario: " + e.getMessage();
        }
    }

    // Obtener DTO por correo
    public ResponseEntity<UsuarioDto> obtenerUserDto(String correo) {
        UsuarioEntity usuario = usuarioRepository.findByCorreo(correo);
        if (usuario != null) {
            UsuarioDto usuarioResponse = new UsuarioDto(
                    usuario.getNombre(),
                    usuario.getCorreo()
            );
            return ResponseEntity.ok(usuarioResponse);
        }
        return ResponseEntity.notFound().build();
    }
}