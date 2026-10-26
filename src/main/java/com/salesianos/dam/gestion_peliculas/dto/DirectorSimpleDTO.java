package com.salesianos.dam.gestion_peliculas.dto;

import com.salesianos.dam.gestion_peliculas.model.Director;

public record DirectorSimpleDTO(

        Long id,
        String nombre

) {

    public static DirectorSimpleDTO of(Director d) {
        return new DirectorSimpleDTO(d.getId(), d.getNombre());
    }

}
