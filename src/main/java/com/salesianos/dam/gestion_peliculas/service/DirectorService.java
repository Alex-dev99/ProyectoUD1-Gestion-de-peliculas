package com.salesianos.dam.gestion_peliculas.service;

import com.salesianos.dam.gestion_peliculas.dto.EditDirectorDTO;
import com.salesianos.dam.gestion_peliculas.error.notFound.DirectorNoEncontradoException;
import com.salesianos.dam.gestion_peliculas.model.Director;
import com.salesianos.dam.gestion_peliculas.repository.DirectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DirectorService {

    private final DirectorRepository directorRepository;

    public Director addDirector(EditDirectorDTO editDirectorDTO){

        return directorRepository.save(editDirectorDTO.to());
    }

    public List<Director> getAllDirectores() {
        List<Director> resultado = directorRepository.findAll();

        return resultado;
    }


    public Director getDirectorById(Long id) {
        return directorRepository.findById(id).orElseThrow(() -> new DirectorNoEncontradoException(id));
    }

    public Director updateDirector(Long id, EditDirectorDTO editDirectorDTO) {
        return directorRepository.findById(id)
                .map( d -> {

                            d.setNombre(editDirectorDTO.nombre());
                            d.setAnioNacimiento(editDirectorDTO.anioNacimiento());
                            return directorRepository.save(d);
                        }).orElseThrow(() -> new DirectorNoEncontradoException(id));
    }

    public void deleteDirector(Long id) {
        directorRepository.deleteById(id);
    }



}
