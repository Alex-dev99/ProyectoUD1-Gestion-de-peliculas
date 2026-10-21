package com.salesianos.dam.gestion_peliculas.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Pelicula {

    @Id
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
