package com.salesianos.dam.gestion_peliculas.service;

import com.salesianos.dam.gestion_peliculas.dto.EditActorDTO;
import com.salesianos.dam.gestion_peliculas.model.Actor;
import com.salesianos.dam.gestion_peliculas.repository.ActorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ActorService {

    private final ActorRepository actorRepository;

    public Actor addActor(EditActorDTO editActorDTO){

        return actorRepository.save(editActorDTO.to());
    }

    public List<Actor> getAllActores() {
        List<Actor> resultado = actorRepository.findAll();

        return resultado;
    }


}
