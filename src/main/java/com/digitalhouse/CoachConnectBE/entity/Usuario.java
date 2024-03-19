package com.digitalhouse.CoachConnectBE.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Usuario")
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String apellido;

    private int edad;

    private String email;

    @Column(name = "contacto_celular")
    private String contactoCelular;

    private String foto;

    private String username;

    private String password;

    private String rol;
}