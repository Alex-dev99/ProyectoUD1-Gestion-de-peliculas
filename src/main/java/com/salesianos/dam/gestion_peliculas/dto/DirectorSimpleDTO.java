package com.salesianos.dam.gestion_peliculas.dto;

public record DirectorSimpleDTO(

        Long id,
        String nombre

) {

    public static DirectorSimpleDTO of(Director d) {
        return new DirectorSimpleDTO(d.getId(), d.getNombre());
    }

}
