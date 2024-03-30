package com.digitalhouse.CoachConnectBE.controller;

public class RoutePaths {
    public static final String TUTORIA = "/tutoria";
    public static final String ESTUDIANTE = "/estudiante";
    public static final String RESENA = "/resena";
    public static final String TUTORIA_RESENA = TUTORIA + "/{id}" + RESENA;
    public static final String ESTUDIANTE_RESENA = ESTUDIANTE + "/{id}" + RESENA;
    public static final String FAVORITO = "/favorito";
    public static final String ESTUDIANTE_FAVORITO = ESTUDIANTE + "/{id}" + FAVORITO;
    public static final String LOGIN = "/login";
    public static final String TUTOR = "/tutor";
    public static final String PROFESION = "/profesion";
    public static final String NIVEL = "/nivel";
    public static final String CATEGORIA = "/categoria";
    public static final String CARACTERISTICA = "/caracteristica";
    public static final String ADMIN = "/admin";
    public static final String RESERVA = "/reserva";
    public static final String USER = "/user";
}
