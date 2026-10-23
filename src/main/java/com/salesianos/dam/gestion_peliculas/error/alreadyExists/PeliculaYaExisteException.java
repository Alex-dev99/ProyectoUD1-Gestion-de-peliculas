package com.salesianos.dam.gestion_peliculas.error.alreadyExists;

public class PeliculaYaExisteException extends EntityAlreadyExistsException {

    public  PeliculaYaExisteException(String titulo) {
        super("La pelicula con titulo: " + titulo + "ya existe en el sistema");
    }


}
