package com.digitalhouse.CoachConnectBE.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "caracteristica")
@Getter
@Setter
@NoArgsConstructor
public class Caracteristica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String icono;

    @ManyToMany(mappedBy = "caracteristicas")
    @JsonIgnore
    private Set<Tutoria> tutorias = new HashSet<>();

    public Caracteristica(Long caracteristicaId) {
        this.id = caracteristicaId;
    }
}