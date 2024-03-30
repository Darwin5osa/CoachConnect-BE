package com.digitalhouse.CoachConnectBE.entity;

import jakarta.persistence.*;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "reserva")
@Setter
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_inicio_reserva")
    private LocalDateTime fechaInicio;

    @Column(name = "fecha_fin_reserva")
    private LocalDateTime fechaFin;

    @Column(name = "horas_reservadas")
    private int horasReservadas;

    @ManyToOne
    @JoinColumn(name = "estudiante_id")
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "tutoria_id")
    private Tutoria tutoria;

    public Long getId() {
        return this.id;
    }

    public LocalDate getFechaInicio() {
        return LocalDate.from(this.fechaInicio);
    }

    public LocalDate getFechaFin() {
        return LocalDate.from(this.fechaFin);
    }

    public int getHorasReservadas() {
        return this.horasReservadas;
    }

    public Estudiante getEstudiante() {
        return this.estudiante;
    }

    public Tutoria getTutoria() {
        return this.tutoria;
    }

    public Long getTutoriaId() {
        return tutoria.getId();
    }

    public Long getEstudianteId() {
        return estudiante.getId();
    }

    public Tutor getTutor() {
        return tutoria.getTutor();
    }
}
