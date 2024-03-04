package com.digitalhouse.CoachConnectBE.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PROFESION")
@Getter
@Setter
@NoArgsConstructor
public class Profesion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @OneToMany(mappedBy = "profesion")
    @JsonIgnore
    private Set<Tutor> tutores = new HashSet<>();

    public Profesion(Long profesionId) {
        this.id = profesionId;
    }
}