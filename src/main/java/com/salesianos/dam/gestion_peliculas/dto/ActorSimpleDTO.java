package com.salesianos.dam.gestion_peliculas.dto;

import com.salesianos.dam.gestion_peliculas.model.Actor;

public record ActorSimpleDTO(

        Long id,
        String nombre

) {

    public static ActorSimpleDTO of (Actor a){

        return new ActorSimpleDTO(

                a.getId(),
                a.getNombre()

        );

    }

}
