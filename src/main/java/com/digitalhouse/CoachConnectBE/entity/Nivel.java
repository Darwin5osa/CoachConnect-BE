package com.digitalhouse.CoachConnectBE.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "NIVEL")
@Getter
@Setter
public class Nivel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @OneToMany(mappedBy = "tutoria")
    @JsonIgnore
    private List<Tutoria> tutorias = new ArrayList<>();
}