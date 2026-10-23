package com.salesianos.dam.gestion_peliculas.error.alreadyExists;

public class EntityAlreadyExistsException extends RuntimeException {
  public EntityAlreadyExistsException(String message) {
    super(message);
  }
}
