package com.salesianos.dam.gestion_peliculas.dto;

import com.salesianos.dam.gestion_peliculas.model.Pelicula;

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
                .nombre(nombre)
                .titulo(titulo)
                .fechaEstreno(fechaEstreno)
                .director(director)
                .actores(actores)
                .build();
    }

}
