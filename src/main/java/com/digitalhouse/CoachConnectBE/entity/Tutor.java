package com.digitalhouse.CoachConnectBE.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "TUTOR")
@Getter
@Setter
@NoArgsConstructor
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "profesion_id")
    private Profesion profesion;

    private String descripcion;

    private Integer calificacion;

    @OneToMany(mappedBy = "tutor")
    @JsonIgnore
    private List<Tutoria> tutorias = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    @OneToMany(mappedBy = "tutor")
    @JsonIgnore
    private Set<Reserva> reserva;

    public Tutor(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return usuario != null ? usuario.getNombre() : null;
    }

    public String getApellido() {
        return usuario != null ? usuario.getApellido() : null;
    }

    public int getEdad() {
        return usuario != null ? usuario.getEdad() : 0;
    }

    public String getEmail() {
        return usuario != null ? usuario.getEmail() : null;
    }

    public String getContactoCelular() {
        return usuario != null ? usuario.getContactoCelular() : null;
    }

    public String getFoto() {
        return usuario != null ? usuario.getFoto() : null;
    }

    public String getUsername() {
        return usuario != null ? usuario.getUsername() : null;
    }

    public Long getProfesionId() {
        return profesion != null ? profesion.getId() : null;
    }
}
