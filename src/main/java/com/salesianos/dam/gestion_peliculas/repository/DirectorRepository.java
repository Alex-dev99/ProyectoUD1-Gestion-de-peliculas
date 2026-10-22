package com.salesianos.dam.gestion_peliculas.repository;

import com.salesianos.dam.gestion_peliculas.model.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface DirectorRepository extends JpaRepository<Director, Long> {
}
