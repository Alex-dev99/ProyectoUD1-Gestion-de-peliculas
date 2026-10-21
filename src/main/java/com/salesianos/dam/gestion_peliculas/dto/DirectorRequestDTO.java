package com.salesianos.dam.gestion_peliculas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record DirectorRequestDTO(

        @NotBlank(message = "El nombre no puede estar vacio")
        String nombre,
        @NotNull(message = "tiene que haber un año de nacimiento")
        @PositiveOrZero(message = "el año de nacimiento no puede ser negarivo")
        Integer anioNacimiento

) {

}
