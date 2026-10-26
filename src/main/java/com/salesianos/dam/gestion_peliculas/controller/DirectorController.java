package com.salesianos.dam.gestion_peliculas.controller;


import com.salesianos.dam.gestion_peliculas.dto.DirectorResponseDTO;
import com.salesianos.dam.gestion_peliculas.dto.EditDirectorDTO;
import com.salesianos.dam.gestion_peliculas.model.Director;
import com.salesianos.dam.gestion_peliculas.repository.DirectorRepository;
import com.salesianos.dam.gestion_peliculas.service.DirectorService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/directores")
@Validated
public class DirectorController {

    private final DirectorService directorService;

    @PostMapping
    public ResponseEntity<DirectorResponseDTO> addDirector(@Valid @RequestBody EditDirectorDTO director) {

        return ResponseEntity.status(201)
                .body(DirectorResponseDTO.of(directorService.addDirector(director)));

    }

    @GetMapping
    public ResponseEntity<List<DirectorResponseDTO>> getAllDirectores() {

        List<Director> result = directorService.getAllDirectores();
        return ResponseEntity.ok(
                result
                        .stream()
                        .map(DirectorResponseDTO::of)
                        .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DirectorResponseDTO> getDirectorById(@PathVariable @Min(value = 1) Long id) {

        return ResponseEntity.ok(DirectorResponseDTO.of(directorService.getDirectorById(id)));

    }

    @PutMapping("/{id}")
    public ResponseEntity<DirectorResponseDTO> updateDirector(
            @PathVariable Long id,
            @Valid @RequestBody EditDirectorDTO director) {

        return ResponseEntity.ok(
                DirectorResponseDTO.of(directorService.updateDirector(id, director)));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDirector(@PathVariable Long id) {
        directorService.deleteDirector(id);
        return ResponseEntity.noContent().build();
    }

}
