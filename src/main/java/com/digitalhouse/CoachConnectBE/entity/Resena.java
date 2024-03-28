package com.digitalhouse.CoachConnectBE.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "resena")
@Getter
@Setter
public class Resena {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "estudiante_id")
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "tutoria_id")
    private Tutoria tutoria;

    @Column(name = "contenido", columnDefinition = "TEXT")
    private String contenido;

    @Column(name = "calificacion")
    private Integer calificacion;

    @Column(name = "fecha_publicacion")
    private LocalDateTime fechaPublicacion;

    public Long getTutoriaId() {
        return tutoria.getId();
    }

    public Long getEstudianteId() {
        return estudiante.getId();
    }
}
