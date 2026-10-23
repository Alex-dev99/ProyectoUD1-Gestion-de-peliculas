package com.salesianos.dam.gestion_peliculas.controller;


import com.salesianos.dam.gestion_peliculas.dto.ActorSimpleDTO;
import com.salesianos.dam.gestion_peliculas.dto.EditActorDTO;
import com.salesianos.dam.gestion_peliculas.model.Actor;
import com.salesianos.dam.gestion_peliculas.repository.ActorRepository;
import com.salesianos.dam.gestion_peliculas.service.ActorService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/actores")
public class ActorController {

    private final ActorRepository actorRepository;
    private final ActorService actorService;

    @PostMapping
    public ResponseEntity<ActorSimpleDTO> addActor(@Valid @RequestBody EditActorDTO actor) {

        return ResponseEntity.status(201)
                .body(ActorSimpleDTO.of(actorService.addActor(actor)));

    }

    @GetMapping
    public ResponseEntity<List<ActorSimpleDTO>> getAllActores() {

        List<Actor> result = actorService.getAllActores();
        return ResponseEntity.ok(
                result
                        .stream()
                        .map(ActorSimpleDTO::of)
                        .toList());
    }


}
