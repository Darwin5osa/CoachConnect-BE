package com.digitalhouse.CoachConnectBE.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TUTORIA")
@Getter
@Setter
public class Tutoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String descripcion;

    @ManyToMany
    @JoinTable(name = "tutoria_caracteristica",
            joinColumns = @JoinColumn(name = "tutoria_id"),
            inverseJoinColumns = @JoinColumn(name = "caracteristica_id"))
    private Set<Caracteristica> caracteristicas = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "NivelId")
    private Nivel nivel;

    @ManyToOne
    @JoinColumn(name = "CategoriaId")
    private Categoria categoria;
}