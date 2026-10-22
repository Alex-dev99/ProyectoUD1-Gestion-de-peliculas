package com.salesianos.dam.gestion_peliculas.dto;

import com.salesianos.dam.gestion_peliculas.model.Director;

public record EditActorDTO(

        String nombre,
) {

    public Actor to(){
        return Actor.builder()
                .nombre(nombre)
                .build();
    }

}
