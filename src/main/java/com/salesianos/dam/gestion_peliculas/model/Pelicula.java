package com.salesianos.dam.gestion_peliculas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pelicula {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String titulo;

    private String genero;
    private LocalDate fechaEstreno;

    @ManyToOne
    private Director director;

    @ManyToMany
    @Builder.Default
    private Set<Actor> actores = new HashSet<>();
}