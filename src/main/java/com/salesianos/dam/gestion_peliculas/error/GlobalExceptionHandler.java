package com.salesianos.dam.gestion_peliculas.error;

import com.salesianos.dam.gestion_peliculas.error.alreadyExists.EntityAlreadyExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ProblemDetail handleEntityNotFound(EntityNotFoundException ex) {

        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problem.setTitle("Recurso no encontrado");
        problem.setDetail(ex.getMessage());
        problem.setType(URI.create("https://api/v1/errors/not-found"));

        return problem;
    }

    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ProblemDetail handleEntityAlreadyExists(EntityAlreadyExistsException ex) {

        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        problem.setTitle("Recurso con esta id ya existe");
        problem.setDetail(ex.getMessage());
        problem.setType(URI.create("https://api/v1/errors/already-exists"));

        return problem;
    }

    @ExceptionHandler(ActorYaAsignadoException.class)
    public ProblemDetail handleActorYaAsignado(ActorYaAsignadoException ex) {
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.CONFLICT);

        problem.setTitle("el actor ya esta asignado a una pelicula");
        problem.setDetail(ex.getMessage());
        problem.setType(URI.create("https://api/v1/errors/already-assignment"));

        return problem;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidation(MethodArgumentNotValidException ex) {

        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problem.setTitle("Error de validación en los datos");
        problem.setDetail("Uno o mas campos contienen datos invalidos.");
        problem.setType(URI.create("https://api/v1/errors/bad-request"));

        return problem;
    }

}

