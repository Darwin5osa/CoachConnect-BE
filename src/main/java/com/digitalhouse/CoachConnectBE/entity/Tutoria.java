package com.digitalhouse.CoachConnectBE.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Entity
@Table(name = "tutoria")
@Getter
@Setter
public class Tutoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String descripcion;

    private String imagenPrincipal;

    private String imagenSecundaria1;

    private String imagenSecundaria2;

    private String imagenSecundaria3;

    private String imagenSecundaria4;

    private String politicas;

    @ManyToMany
    @JoinTable(
            name = "caracteristica_tutoria",
            inverseJoinColumns = @JoinColumn(name = "caracteristica_id"),
            joinColumns = @JoinColumn(name = "tutoria_id")
    )
    private Set<Caracteristica> caracteristicas = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "nivel_educativo_id")
    private Nivel nivel;

    @ManyToOne
    @JoinColumn(name = "tutor_id")
    private Tutor tutor;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
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

    public List<String> getAllImages() {
        return Arrays.asList(imagenPrincipal, imagenSecundaria1, imagenSecundaria2, imagenSecundaria3, imagenSecundaria4);
    }

    public void setImages(List<String> fotos) {
        this.imagenPrincipal = fotos.get(0);
        this.imagenSecundaria1 = fotos.get(1);
        this.imagenSecundaria2 = fotos.get(2);
        this.imagenSecundaria3 = fotos.get(3);
        this.imagenSecundaria4 = fotos.get(4);
    }
}