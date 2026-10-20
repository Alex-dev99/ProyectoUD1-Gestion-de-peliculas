package com.salesianos.dam.gestion_peliculas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Pelicula {

    @GeneratedValue
    private Long id;

    private String titulo;
    private String genero;
    private LocalDate fechaEstreno;

    @ManyToOne
    private Director director;

    @ManyToMany
    private List<Actor> actores;
}
