package com.salesianos.dam.gestion_peliculas.controller;

import com.salesianos.dam.gestion_peliculas.dto.EditPeliculaDTO;
import com.salesianos.dam.gestion_peliculas.dto.PeliculaResponseDTO;
import com.salesianos.dam.gestion_peliculas.model.Pelicula;
import com.salesianos.dam.gestion_peliculas.repository.PeliculaRepository;
import com.salesianos.dam.gestion_peliculas.service.PeliculaService;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/peliculas")
public class PeliculaController {

    private final PeliculaRepository peliculaRepository;
    private final PeliculaService peliculaService;

    @PostMapping
    public ResponseEntity<PeliculaResponseDTO> addPeliculas(@RequestBody EditPeliculaDTO pelicula) {

        return ResponseEntity.status(201)
                .body(PeliculaResponseDTO.of(peliculaService.addPelicula(pelicula)));

    }

    @GetMapping
    public ResponseEntity<List<PeliculaResponseDTO>> getAllPeliculas() {

        List<Pelicula> result = peliculaService.getAllPeliculas();
        return ResponseEntity.ok(
                result
                        .stream()
                        .map(PeliculaResponseDTO::of)
                        .toList());
    }

    @GetMapping("/{peliculaId}")
    public ResponseEntity<PeliculaResponseDTO> getPeliculasById(@PathVariable @Min(value = 1) Long id) {

        return ResponseEntity.ok(PeliculaResponseDTO.of(peliculaService.getPeliculaById(id)));

    }

    @PutMapping("/{id}")
    public ResponseEntity<PeliculaResponseDTO> updatePelicula(
            @PathVariable Long id,
            @RequestBody EditPeliculaDTO pelicula) {

        return ResponseEntity.ok(
                PeliculaResponseDTO.of(peliculaService.updatePelicula(id, pelicula)));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePelicula(@PathVariable Long id) {
        peliculaService.deletePelicula(id);
        return ResponseEntity.noContent().build();
    }

}
