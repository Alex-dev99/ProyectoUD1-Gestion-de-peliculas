package com.salesianos.dam.gestion_peliculas.dto;

import com.salesianos.dam.gestion_peliculas.model.Director;

public record EditDirectorDTO(

        String nombre,
        Integer anioNacimiento
) {

    public Director to(){
        return Director.builder()
                .nombre(nombre)
                .anioNacimiento(anioNacimiento)
                .build();
    }

}
