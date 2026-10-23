package com.salesianos.dam.gestion_peliculas.validation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;


import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DirectorMayorDeEdadValidator.class)
@Documented
public @interface DirectorMayorDeEdad {

    String message() default "El director debe ser mayor de edad (18 o mas)";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
