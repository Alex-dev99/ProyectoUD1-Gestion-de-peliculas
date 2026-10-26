package com.salesianos.dam.gestion_peliculas.dto;

import com.salesianos.dam.gestion_peliculas.model.Actor;
import jakarta.validation.constraints.NotBlank;

public record EditActorDTO(

        @NotBlank(message = "El nombre no puede estar vacio")
        String nombre
) {

    public Actor to(){
        return Actor.builder()
                .nombre(nombre)
                .build();
    }

}
