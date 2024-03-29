package com.digitalhouse.CoachConnectBE.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "resena")
@Getter
@Setter
public class Favorito {
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

    public Long getTutoriaId() {
        return tutoria.getId();
    }

    public Long getEstudianteId() {
        return estudiante.getId();
    }
}
