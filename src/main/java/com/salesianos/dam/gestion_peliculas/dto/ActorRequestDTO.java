package com.salesianos.dam.gestion_peliculas.dto;

import jakarta.validation.constraints.NotBlank;

public record ActorRequestDTO(

        @NotBlank(message = "El nombre no puede estar vacio")
        String nombre

) {
}
