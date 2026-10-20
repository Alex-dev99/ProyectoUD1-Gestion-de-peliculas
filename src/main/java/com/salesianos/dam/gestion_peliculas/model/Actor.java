package com.salesianos.dam.gestion_peliculas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.Data;

@Data
@Entity
public class Actor {

    @GeneratedValue
    private Long id;

    private String nombre;
}
