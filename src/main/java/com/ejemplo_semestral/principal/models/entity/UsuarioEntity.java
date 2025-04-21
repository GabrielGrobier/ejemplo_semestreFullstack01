package com.ejemplo_semestral.principal.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //Columna llamada nombre que asocia el atributo nombre 
    @Column(name = "nombre")
    private String nombre;

    private String apellido;
    private String correo;
    

    
}
