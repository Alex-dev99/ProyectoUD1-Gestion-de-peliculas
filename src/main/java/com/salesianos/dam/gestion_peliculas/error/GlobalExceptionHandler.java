package com.salesianos.dam.gestion_peliculas.error;

import com.salesianos.dam.gestion_peliculas.error.alreadyExists.EntityAlreadyExistsException;
import com.salesianos.dam.gestion_peliculas.error.notFound.EntityNotFoundException;
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
        problem.setTitle("Recurso ya existente");
        problem.setDetail(ex.getMessage());
        problem.setType(URI.create("https://api/v1/errors/already-exists"));
        return problem;
    }

    @ExceptionHandler(ActorYaAsignadoException.class)
    public ProblemDetail handleActorYaAsignado(ActorYaAsignadoException ex) {
        ProblemDetail problem = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        problem.setTitle("El actor ya esta asignado a la pelicula");
        problem.setDetail(ex.getMessage());
        problem.setType(URI.create("https://api/v1/errors/already-assignment"));
        return problem;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ProblemDetail handleConstraintViolation(ConstraintViolationException ex) {
        ProblemDetail result = ProblemDetail
                .forStatusAndDetail(HttpStatus.BAD_REQUEST, "Error de validación");

        List<ApiValidationSubError> subErrors = ex.getConstraintViolations().stream()
                .map(ApiValidationSubError::from)
                .toList();

        result.setProperty("invalid-params", subErrors);
        result.setType(URI.create("https://api/v1/errors/bad-request"));
        return result;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        ProblemDetail result = ProblemDetail
                .forStatusAndDetail(HttpStatus.BAD_REQUEST, "Error de validacion");

        List<ApiValidationSubError> subErrors = ex.getAllErrors().stream()
                .map(ApiValidationSubError::from)
                .toList();

        result.setProperty("invalid-params", subErrors);
        result.setType(URI.create("https://api/v1/errors/bad-request"));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    record ApiValidationSubError(
            String object,
            String message,
            String field,
            Object rejectedValue
    ) {

        public static ApiValidationSubError from(ObjectError error) {
            if (error instanceof FieldError fieldError) {
                return new ApiValidationSubError(
                        fieldError.getObjectName(),
                        fieldError.getDefaultMessage(),
                        fieldError.getField(),
                        fieldError.getRejectedValue());
            }
            return new ApiValidationSubError(
                    error.getObjectName(), error.getDefaultMessage(), null, null);
        }

        public static ApiValidationSubError from(ConstraintViolation<?> v) {
            return new ApiValidationSubError(
                    v.getRootBeanClass().getSimpleName(),
                    v.getMessage(),
                    getFieldName(v),
                    v.getInvalidValue());
        }

        private static String getFieldName(ConstraintViolation<?> v) {
            String field = null;
            for (Path.Node node : v.getPropertyPath()) {
                if (node.getKind() == ElementKind.PROPERTY || node.getKind() == ElementKind.PARAMETER) {
                    field = node.getName() != null ? node.getName() : node.toString();
                }
            }
            return field;
        }
    }
}

