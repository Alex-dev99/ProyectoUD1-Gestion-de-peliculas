package com.salesianos.dam.gestion_peliculas.dto;

import com.salesianos.dam.gestion_peliculas.model.Director;

public record DirectorResponseDTO(

         Long id,
         String nombre,
         Integer anioNacimiento
) {

    public static DirectorResponseDTO of (Director d){

        return new DirectorResponseDTO(

                d.getId(),
                d.getNombre(),
                d.getAnioNacimiento()

        );

    }

}
