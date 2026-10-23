package com.salesianos.dam.gestion_peliculas.error.notFound;

import jakarta.persistence.EntityNotFoundException;

public class PeliculaNoEncontradaException extends EntityNotFoundException {

    public PeliculaNoEncontradaException() {
        super("pelicula no encontrada");
    }

    public  PeliculaNoEncontradaException(Long id) {
        super("pelicula no encontrada cuya id sea: " + id);
    }

    public PeliculaNoEncontradaException(String message) {
        super(message);
    }

}
