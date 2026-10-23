package com.salesianos.dam.gestion_peliculas.error;

public class ActorYaAsignadoException extends RuntimeException {

    public ActorYaAsignadoException(Long actorId, String  titulo) {
        super("El actor con ID: " + actorId + " ya se encuentra asignado a la pelicula: " + titulo);
    }
}