package com.salesianos.dam.gestion_peliculas.error.notFound;

public class DirectorNoEncontradoException extends EntityNotFoundException {

    public DirectorNoEncontradoException() {
        super("director no encontrado");
    }

    public  DirectorNoEncontradoException(Long id) {
        super("director no encontrado cuya id sea: " + id);
    }

    public DirectorNoEncontradoException(String message) {
        super(message);
    }

}
