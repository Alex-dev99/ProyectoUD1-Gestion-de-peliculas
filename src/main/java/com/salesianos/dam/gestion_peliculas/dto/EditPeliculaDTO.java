package com.salesianos.dam.gestion_peliculas.dto;

import com.salesianos.dam.gestion_peliculas.model.Actor;
import com.salesianos.dam.gestion_peliculas.model.Director;
import com.salesianos.dam.gestion_peliculas.model.Pelicula;

import java.time.LocalDate;
import java.util.Set;

public record EditPeliculaDTO(

        @NotBlank(message = "El titulo no puede estar vacio")
        String titulo,

        @NotBlank(message = "El genero no puede estar vacio")
        String genero,

        @NotNull(message = "La fecha de estreno es obligatoria")
        LocalDate fechaEstreno,

        @NotNull(message = "El ID del director no puede estar vacio")
        Long directorId
) {

    public Pelicula to(Director director){
        return Pelicula.builder()
                .titulo(titulo)
                .genero(genero)
                .fechaEstreno(fechaEstreno)
                .director(director)
                .build();
    }

}
