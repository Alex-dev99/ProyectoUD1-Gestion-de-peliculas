package com.salesianos.dam.gestion_peliculas.controller;


import com.salesianos.dam.gestion_peliculas.dto.DirectorResponseDTO;
import com.salesianos.dam.gestion_peliculas.dto.EditDirectorDTO;
import com.salesianos.dam.gestion_peliculas.service.DirectorService;
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
@RequestMapping("/api/v1/directores")
@Validated
public class DirectorController {

    private final DirectorService directorService;

    @Operation(description = "Crea un director")
    @ApiResponse(responseCode = "201", description = "Director creado")
    @ApiResponse(responseCode = "400", description = "Datos invalidos")
    @PostMapping
    public ResponseEntity<DirectorResponseDTO> addDirector(
            @Valid @RequestBody EditDirectorDTO director) {
        return ResponseEntity.status(201)
                .body(DirectorResponseDTO.of(directorService.addDirector(director)));
    }

    @Operation(description = "Lista todos los directores")
    @ApiResponse(responseCode = "200", description = "Lista devuelta")
    @GetMapping
    public ResponseEntity<List<DirectorResponseDTO>> getAllDirectores() {
        return ResponseEntity.ok(
                directorService.getAllDirectores()
                        .stream()
                        .map(DirectorResponseDTO::of)
                        .toList());
    }

    @Operation(description = "Busca un director por ID")
    @ApiResponse(responseCode = "200", description = "Director encontrado")
    @ApiResponse(responseCode = "404", description = "Director no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<DirectorResponseDTO> getDirectorById(
            @PathVariable @Min(value = 1) Long id) {
        return ResponseEntity.ok(
                DirectorResponseDTO.of(directorService.getDirectorById(id)));
    }

    @Operation(description = "Modifica un director existente")
    @ApiResponse(responseCode = "200", description = "Director actualizado")
    @ApiResponse(responseCode = "400", description = "Datos invalidos")
    @ApiResponse(responseCode = "404", description = "Director no encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<DirectorResponseDTO> updateDirector(
            @PathVariable Long id,
            @Valid @RequestBody EditDirectorDTO director) {
        return ResponseEntity.ok(
                DirectorResponseDTO.of(directorService.updateDirector(id, director)));
    }

    @Operation(description = "Elimina un director por ID")
    @ApiResponse(responseCode = "204", description = "Director eliminado")
    @ApiResponse(responseCode = "404", description = "Director no encontrado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDirector(@PathVariable Long id) {
        directorService.deleteDirector(id);
        return ResponseEntity.noContent().build();
    }
}
