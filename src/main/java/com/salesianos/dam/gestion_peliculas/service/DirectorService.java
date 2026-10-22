package com.salesianos.dam.gestion_peliculas.service;

import com.salesianos.dam.gestion_peliculas.dto.EditDirectorDTO;
import com.salesianos.dam.gestion_peliculas.model.Director;
import com.salesianos.dam.gestion_peliculas.repository.DirectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    //por ahora son opcionals por si acaso no da tiempo ha hacer excepciones

    public Optional<Director> getDirectorById(Long id) {
        return directorRepository.findById(id);
    }

    public Optional<Director> updateDirector(Long id, EditDirectorDTO editDirectorDTO) {
        return directorRepository.findById(id)
                .map( d -> {

                            d.setNombre(editDirectorDTO.nombre());
                            d.setAnioNacimiento(editDirectorDTO.anioNacimiento());
                            return directorRepository.save(d);
                        });
    }

    public void deleteDirector(Director d) {
        directorRepository.delete(d);
    }



}
