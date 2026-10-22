package com.salesianos.dam.gestion_peliculas.service;

import com.salesianos.dam.gestion_peliculas.dto.EditPeliculaDTO;
import com.salesianos.dam.gestion_peliculas.model.Pelicula;
import com.salesianos.dam.gestion_peliculas.repository.PeliculaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PeliculaService {

    private final PeliculaRepository peliculaRepository;


}
