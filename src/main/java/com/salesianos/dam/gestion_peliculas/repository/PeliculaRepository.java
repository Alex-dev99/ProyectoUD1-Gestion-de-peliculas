package com.salesianos.dam.gestion_peliculas.repository;

import com.salesianos.dam.gestion_peliculas.model.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeliculaRepository extends JpaRepository<Pelicula, Long> {

    boolean existsByTitulo(String titulo);

}
