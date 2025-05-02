package com.ejemplo_semestral.principal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ejemplo_semestral.principal.models.entity.UsuarioEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer>{
    UsuarioEntity findByCorreo(String correo);
    Boolean existsByCorreo(String correo);
    void deleteByCorreo(String correo);
    UsuarioEntity findUsuarioById(int id);
    Boolean existsById(int id);
    //Boolean findByCorreoAndPassword(String correo , String password)
    // save 
    //findAll

}  

    

