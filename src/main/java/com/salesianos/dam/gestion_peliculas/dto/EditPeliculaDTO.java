package com.salesianos.dam.gestion_peliculas.dto;

import com.salesianos.dam.gestion_peliculas.model.Actor;
import com.salesianos.dam.gestion_peliculas.model.Director;
import com.salesianos.dam.gestion_peliculas.model.Pelicula;

import java.time.LocalDate;
import java.util.Set;

public record EditPeliculaDTO(

        Long id,
        String titulo,
        String genero,
        LocalDate fechaEstreno,
        Long directorId,
        Set<Actor> actores
) {

    public Pelicula to(){
        return Pelicula.builder()
                .titulo(titulo)
                .fechaEstreno(fechaEstreno)
                .directorId(directorId)
                .actores(actores)
                .build();
    }

}
