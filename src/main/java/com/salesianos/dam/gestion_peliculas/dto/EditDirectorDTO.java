package com.salesianos.dam.gestion_peliculas.dto;

import com.salesianos.dam.gestion_peliculas.model.Director;
import com.salesianos.dam.gestion_peliculas.validation.DirectorMayorDeEdad;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record EditDirectorDTO(
        @NotBlank(message = "El nombre no puede estar vacio")
        String nombre,

        @NotNull(message = "tiene que haber un año de nacimiento")
        @PositiveOrZero(message = "el año de nacimiento no puede ser negativo")
        @DirectorMayorDeEdad
        Integer anioNacimiento
) {

    public Director to(){
        return Director.builder()
                .nombre(nombre)
                .anioNacimiento(anioNacimiento)
                .build();
    }

}
