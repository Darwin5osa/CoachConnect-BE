package com.digitalhouse.CoachConnectBE.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TUTOR")
@Getter
@Setter
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "profesionId")
    private Profesion profesion;

    private String descripcion;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

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

    public String getFoto() {
        return usuario != null ? usuario.getFoto() : null;
    }

}
