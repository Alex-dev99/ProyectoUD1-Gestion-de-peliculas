package com.salesianos.dam.gestion_peliculas.error.notFound;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
