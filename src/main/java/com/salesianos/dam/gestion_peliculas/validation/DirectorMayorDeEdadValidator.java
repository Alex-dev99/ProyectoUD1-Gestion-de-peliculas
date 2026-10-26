package com.salesianos.dam.gestion_peliculas.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.Year;

public class DirectorMayorDeEdadValidator implements ConstraintValidator<DirectorMayorDeEdad, Integer> {

    @Override
    public boolean isValid(Integer anioNacimiento, ConstraintValidatorContext context) {
        if (anioNacimiento == null) {
            return true;
        }

        int anioActual = Year.now().getValue();

        int edadEstimada = anioActual - anioNacimiento;

        return edadEstimada >= 18;
    }
}
