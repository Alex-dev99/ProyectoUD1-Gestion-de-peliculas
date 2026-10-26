package com.salesianos.dam.gestion_peliculas.dto;

import com.salesianos.dam.gestion_peliculas.model.Director;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PeliculaRequestDTO(

         @NotBlank(message = "El titulo no puede estar vacio")
         String titulo,
         @NotBlank(message = "El genero no puede estar vacio")
         String genero,
         LocalDate fechaEstreno,
         @NotNull(message = "El id del director no puede estar vacio")
         Long directorId

) {
}
