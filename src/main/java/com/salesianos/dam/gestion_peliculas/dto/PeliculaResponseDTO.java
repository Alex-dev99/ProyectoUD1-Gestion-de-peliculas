package com.salesianos.dam.gestion_peliculas.dto;

import com.salesianos.dam.gestion_peliculas.model.Actor;
import com.salesianos.dam.gestion_peliculas.model.Director;
import com.salesianos.dam.gestion_peliculas.model.Pelicula;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public record PeliculaResponseDTO(

        Long id,
        String titulo,
        String genero,
        LocalDate fechaEstreno,
        DirectorSimpleDTO director,
        List<ActorSimpleDTO> actores

        ) {

        public static PeliculaResponseDTO of (Pelicula p){

                return new PeliculaResponseDTO(
                        p.getId(),
                        p.getTitulo(),
                        p.getGenero(),
                        p.getFechaEstreno(),
                        DirectorSimpleDTO.of(p.getDirector()),
                        p.getActores().stream()
                                .map(ActorSimpleDTO::of)
                                .toList()

                );
        }
}
