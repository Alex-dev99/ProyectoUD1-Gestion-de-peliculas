package com.salesianos.dam.gestion_peliculas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Director {

    @Id
    @GeneratedValue
    private Long id;

    private String nombre;
    private Integer anioNacimiento;

}
