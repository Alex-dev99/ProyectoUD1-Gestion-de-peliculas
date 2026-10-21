package com.salesianos.dam.gestion_peliculas.dto;

import java.time.LocalDate;
import java.util.List;

public record PeliculaResponseDTO(

        Long id,
        String titulo,
        String genero,
        LocalDate fechaEstreno,
        DirectorSimpleDTO director,
        List<ActorSimpleDTO> actores

        ) {
}
