package com.salesianos.dam.gestion_peliculas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
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

    @NotBlank(message = "El titulo no puede estar vacio")
    @Column(unique = true)
    private String titulo;

    @NotBlank(message = "El genero no puede estar vacio")
    private String genero;
    @NotNull(message = "La fecha de estreno es obligatoria")
    private LocalDate fechaEstreno;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "director_id")
    @NotNull(message = "El ID del director no puede estar vacio")
    private Director director;

    @ManyToMany
    @Builder.Default
    private Set<Actor> actores = new HashSet<>();

}
