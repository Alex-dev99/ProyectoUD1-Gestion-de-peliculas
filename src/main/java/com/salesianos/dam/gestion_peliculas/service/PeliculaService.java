package com.salesianos.dam.gestion_peliculas.service;

import com.salesianos.dam.gestion_peliculas.dto.EditPeliculaDTO;
import com.salesianos.dam.gestion_peliculas.error.ActorYaAsignadoException;
import com.salesianos.dam.gestion_peliculas.error.alreadyExists.PeliculaYaExisteException;
import com.salesianos.dam.gestion_peliculas.error.notFound.PeliculaNoEncontradaException;
import com.salesianos.dam.gestion_peliculas.model.Actor;
import com.salesianos.dam.gestion_peliculas.model.Director;
import com.salesianos.dam.gestion_peliculas.model.Pelicula;
import com.salesianos.dam.gestion_peliculas.repository.PeliculaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PeliculaService {

    private final PeliculaRepository peliculaRepository;
    private final DirectorService directorService;
    private final ActorService actorService;

    public Director getDirectorById(Long directorId) {
        return directorService.getDirectorById(directorId);
    }

    public Actor getActorById(Long actorId) {
        return actorService.getActorById(actorId);
    }

    public Pelicula addPelicula(EditPeliculaDTO editPeliculaDTO) {
        if (peliculaRepository.existsByTitulo(editPeliculaDTO.titulo())) {
            throw new PeliculaYaExisteException(editPeliculaDTO.titulo());
        }
        Director director = getDirectorById(editPeliculaDTO.directorId());
        return peliculaRepository.save(editPeliculaDTO.to(director));
    }

    public List<Pelicula> getAllPeliculas() {
        return peliculaRepository.findAll();
    }

    public Pelicula getPeliculaById(Long id) {
        return peliculaRepository.findById(id)
                .orElseThrow(() -> new PeliculaNoEncontradaException(id));
    }

    public Pelicula updatePelicula(Long id, EditPeliculaDTO editPeliculaDTO) {
        return peliculaRepository.findById(id)
                .map(p -> {
                    Director director = getDirectorById(editPeliculaDTO.directorId());
                    p.setTitulo(editPeliculaDTO.titulo());
                    p.setGenero(editPeliculaDTO.genero());
                    p.setFechaEstreno(editPeliculaDTO.fechaEstreno());
                    p.setDirector(director);
                    return peliculaRepository.save(p);
                })
                .orElseThrow(() -> new PeliculaNoEncontradaException(id));
    }

    public void deletePelicula(Long id) {
        peliculaRepository.deleteById(id);
    }

    public Pelicula addActor(Long peliculaId, Long actorId) {
        Pelicula pelicula = getPeliculaById(peliculaId);
        Actor actor = getActorById(actorId);

        boolean yaAsignado = pelicula.getActores().stream()
                .anyMatch(a -> a.getId().equals(actorId));
        if (yaAsignado) {
            throw new ActorYaAsignadoException(actorId, pelicula.getTitulo());
        }

        pelicula.getActores().add(actor);
        return peliculaRepository.save(pelicula);
    }
}