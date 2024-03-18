package com.digitalhouse.CoachConnectBE.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "nivel_educativo")
@Getter
@Setter
@NoArgsConstructor
public class Nivel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @OneToMany(mappedBy = "nivel")
    @JsonIgnore
    private List<Tutoria> tutorias = new ArrayList<>();

    public Nivel(Long nivelId) {
        this.id = nivelId;
    }
}