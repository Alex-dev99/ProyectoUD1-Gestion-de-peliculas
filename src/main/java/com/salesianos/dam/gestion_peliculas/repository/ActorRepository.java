package com.salesianos.dam.gestion_peliculas.repository;

import com.salesianos.dam.gestion_peliculas.model.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActorRepository extends JpaRepository<Actor, Long> {

}
