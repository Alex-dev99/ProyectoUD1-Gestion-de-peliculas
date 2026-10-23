package com.salesianos.dam.gestion_peliculas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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

    private String titulo;

    @NotBlank(message = "El genero no puede estar vacio")
    private String genero;
    @NotNull(message = "La fecha de estreno es obligatoria")
    private LocalDate fechaEstreno;

    @ManyToOne
    @NotNull(message = "El ID del director no puede estar vacio")
    private Long directorId;

    @ManyToMany
    private Set<Actor> actores;

}
