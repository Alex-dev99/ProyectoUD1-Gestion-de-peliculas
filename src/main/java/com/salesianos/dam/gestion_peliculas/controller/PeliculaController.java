package com.salesianos.dam.gestion_peliculas.controller;

import com.salesianos.dam.gestion_peliculas.dto.EditPeliculaDTO;
import com.salesianos.dam.gestion_peliculas.dto.PeliculaResponseDTO;
import com.salesianos.dam.gestion_peliculas.service.PeliculaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/peliculas")
@Validated
public class PeliculaController {

    private final PeliculaService peliculaService;

    @Operation(description = "Crea una pelicula asociada a un director")
    @ApiResponse(responseCode = "201", description = "Pelicula creada")
    @ApiResponse(responseCode = "400", description = "Datos invalidos o director inexistente")
    @ApiResponse(responseCode = "409", description = "Titulo de pelicula duplicado")
    @PostMapping
    public ResponseEntity<PeliculaResponseDTO> addPelicula(
            @Valid @RequestBody EditPeliculaDTO pelicula) {
        return ResponseEntity.status(201)
                .body(PeliculaResponseDTO.of(peliculaService.addPelicula(pelicula)));
    }

    @Operation(description = "Asigna un actor al reparto de una pelicula")
    @ApiResponse(responseCode = "201", description = "Actor asignado")
    @ApiResponse(responseCode = "404", description = "Pelicula o actor no encontrado")
    @ApiResponse(responseCode = "409", description = "El actor ya estaba asignado")
    @PostMapping("/{peliculaId}/actores/{actorId}")
    public ResponseEntity<PeliculaResponseDTO> addActor(
            @PathVariable @Min(value = 1) Long peliculaId,
            @PathVariable @Min(value = 1) Long actorId) {
        return ResponseEntity.status(201)
                .body(PeliculaResponseDTO.of(peliculaService.addActor(peliculaId, actorId)));
    }

    @Operation(description = "Lista todas las peliculas con su director y reparto")
    @ApiResponse(responseCode = "200", description = "Lista devuelta")
    @GetMapping
    public ResponseEntity<List<PeliculaResponseDTO>> getAllPeliculas() {
        return ResponseEntity.ok(
                peliculaService.getAllPeliculas()
                        .stream()
                        .map(PeliculaResponseDTO::of)
                        .toList());
    }

    @Operation(description = "Busca una pelicula por ID")
    @ApiResponse(responseCode = "200", description = "Pelicula encontrada")
    @ApiResponse(responseCode = "404", description = "Pelicula no encontrada")
    @GetMapping("/{peliculaId}")
    public ResponseEntity<PeliculaResponseDTO> getPeliculaById(
            @PathVariable @Min(value = 1) Long peliculaId) {
        return ResponseEntity.ok(
                PeliculaResponseDTO.of(peliculaService.getPeliculaById(peliculaId)));
    }

    @Operation(description = "Modifica una pelicula existente")
    @ApiResponse(responseCode = "200", description = "Pelicula actualizada")
    @ApiResponse(responseCode = "400", description = "Datos invalidos")
    @ApiResponse(responseCode = "404", description = "Pelicula o director no encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<PeliculaResponseDTO> updatePelicula(
            @PathVariable Long id,
            @Valid @RequestBody EditPeliculaDTO pelicula) {
        return ResponseEntity.ok(
                PeliculaResponseDTO.of(peliculaService.updatePelicula(id, pelicula)));
    }

    @Operation(description = "Elimina una pelicula por ID")
    @ApiResponse(responseCode = "204", description = "Pelicula eliminada")
    @ApiResponse(responseCode = "404", description = "Pelicula no encontrada")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePelicula(@PathVariable Long id) {
        peliculaService.deletePelicula(id);
        return ResponseEntity.noContent().build();
    }
}
