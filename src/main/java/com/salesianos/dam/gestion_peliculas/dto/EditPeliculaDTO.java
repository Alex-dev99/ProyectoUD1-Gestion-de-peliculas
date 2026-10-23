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
        Director director,
        Set<Actor> actores
) {

    public Pelicula to(){
        return Pelicula.builder()
                .titulo(titulo)
                .fechaEstreno(fechaEstreno)
                .director(director)
                .actores(actores)
                .build();
    }

}
