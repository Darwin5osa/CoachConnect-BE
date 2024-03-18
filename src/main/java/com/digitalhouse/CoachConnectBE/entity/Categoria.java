package com.digitalhouse.CoachConnectBE.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categoria")
@Getter
@Setter
@NoArgsConstructor
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String descripcion;

    private String foto;

    @ManyToMany(mappedBy = "categoria")
    @JsonIgnore
    private Set<Tutoria> tutorias = new HashSet<>();

    public Categoria(Long categoriaId) {
        this.id = categoriaId;
    }
}