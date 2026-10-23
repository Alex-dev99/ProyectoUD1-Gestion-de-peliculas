package com.salesianos.dam.gestion_peliculas.service;

import com.salesianos.dam.gestion_peliculas.dto.EditPeliculaDTO;
import com.salesianos.dam.gestion_peliculas.error.notFound.ActorNoEncontradoException;
import com.salesianos.dam.gestion_peliculas.error.notFound.DirectorNoEncontradoException;
import com.salesianos.dam.gestion_peliculas.error.notFound.PeliculaNoEncontradaException;
import com.salesianos.dam.gestion_peliculas.model.Actor;
import com.salesianos.dam.gestion_peliculas.model.Director;
import com.salesianos.dam.gestion_peliculas.model.Pelicula;
import com.salesianos.dam.gestion_peliculas.repository.ActorRepository;
import com.salesianos.dam.gestion_peliculas.repository.DirectorRepository;
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

    public Pelicula addPelicula(EditPeliculaDTO editPeliculaDTO){
        getDirectorById(editPeliculaDTO.director().getId());
        return peliculaRepository.save(editPeliculaDTO.to());
    }

    public Pelicula addActor(Long actorId, EditPeliculaDTO editPeliculaDTO){

        Actor actor = getActorById(actorId);

        editPeliculaDTO.actores().add(actor);

        return peliculaRepository.save(editPeliculaDTO.to());

    }

    public List<Pelicula> getAllPeliculas() {
        List<Pelicula> resultado = peliculaRepository.findAll();

        return resultado;
    }

    public Pelicula getPeliculaById(Long id) {
        return peliculaRepository.findById(id).orElseThrow(() -> new PeliculaNoEncontradaException(id));
    }

    public Pelicula updatePelicula(Long id, EditPeliculaDTO editPeliculaDTO) {

        return peliculaRepository.findById(id)
                .map(p -> {
                    p.setTitulo(editPeliculaDTO.titulo());
                    p.setFechaEstreno(editPeliculaDTO.fechaEstreno());
                    p.setGenero(editPeliculaDTO.genero());
                    p.setDirector(editPeliculaDTO.director());
                    p.setActores(editPeliculaDTO.actores());
                    return peliculaRepository.save(p);
                })
                .orElseThrow(() -> new PeliculaNoEncontradaException(id));
    }

    public void deletePelicula(Long id) {
        peliculaRepository.deleteById(id);
    }

}
