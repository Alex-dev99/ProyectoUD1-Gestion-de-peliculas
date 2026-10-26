package com.salesianos.dam.gestion_peliculas.controller;

import com.salesianos.dam.gestion_peliculas.dto.EditPeliculaDTO;
import com.salesianos.dam.gestion_peliculas.dto.PeliculaResponseDTO;
import com.salesianos.dam.gestion_peliculas.model.Pelicula;
import com.salesianos.dam.gestion_peliculas.repository.PeliculaRepository;
import com.salesianos.dam.gestion_peliculas.service.PeliculaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/peliculas")
@Validated
public class PeliculaController {

    private final PeliculaService peliculaService;

    @PostMapping
    public ResponseEntity<PeliculaResponseDTO> addPelicula(
            @Valid @RequestBody EditPeliculaDTO pelicula) {

        return ResponseEntity.status(201)
                .body(PeliculaResponseDTO.of(peliculaService.addPelicula(pelicula)));
    }

    @PostMapping("/{peliculaId}/actores/{actorId}")
    public ResponseEntity<PeliculaResponseDTO> addActor(
            @PathVariable @Min(value = 1) Long peliculaId,
            @PathVariable @Min(value = 1) Long actorId) {

        return ResponseEntity.status(201)
                .body(PeliculaResponseDTO.of(peliculaService.addActor(peliculaId, actorId)));
    }

    @GetMapping
    public ResponseEntity<List<PeliculaResponseDTO>> getAllPeliculas() {
        return ResponseEntity.ok(
                peliculaService.getAllPeliculas()
                        .stream()
                        .map(PeliculaResponseDTO::of)
                        .toList());
    }

    @GetMapping("/{peliculaId}")
    public ResponseEntity<PeliculaResponseDTO> getPeliculaById(
            @PathVariable @Min(value = 1) Long peliculaId) {

        return ResponseEntity.ok(
                PeliculaResponseDTO.of(peliculaService.getPeliculaById(peliculaId)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PeliculaResponseDTO> updatePelicula(
            @PathVariable Long id,
            @Valid @RequestBody EditPeliculaDTO pelicula) {

        return ResponseEntity.ok(
                PeliculaResponseDTO.of(peliculaService.updatePelicula(id, pelicula)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePelicula(@PathVariable Long id) {
        peliculaService.deletePelicula(id);
        return ResponseEntity.noContent().build();
    }

}
