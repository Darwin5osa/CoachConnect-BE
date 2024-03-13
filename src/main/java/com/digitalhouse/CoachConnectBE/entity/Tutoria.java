package com.digitalhouse.CoachConnectBE.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
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
    @JoinColumn(name = "nivelId")
    private Nivel nivel;

    @ManyToOne
    @JoinColumn(name = "tutorId")
    private Tutor tutor;

    @ManyToOne
    @JoinColumn(name = "categoriaId")
    private Categoria categoria;

    public Long getNivelId() {
        return nivel != null ? nivel.getId() : null;
    }

    public Long getTutorId() {
        return tutor != null ? tutor.getId() : null;
    }

    public Long getCategoriaId() {
        return categoria != null ? categoria.getId() : null;
    }

    public List<Long> getCaracteristicasIds() {
        return caracteristicas.stream().map(Caracteristica::getId).toList();
    }
}