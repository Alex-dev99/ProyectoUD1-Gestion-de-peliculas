package com.salesianos.dam.gestion_peliculas.error.notFound;

import jakarta.persistence.EntityNotFoundException;

public class ActorNoEncontradoException extends EntityNotFoundException {

    public ActorNoEncontradoException() {
        super("actor no encontrado");
    }

    public  ActorNoEncontradoException(Long id) {
        super("actor no encontrado cuya id sea: " + id);
    }

    public ActorNoEncontradoException(String message) {
        super(message);
    }

}
