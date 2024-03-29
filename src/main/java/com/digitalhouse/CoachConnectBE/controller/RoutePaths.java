package com.digitalhouse.CoachConnectBE.controller;

public class RoutePaths {
    public static final String TUTORIA = "/tutoria";
    public static final String ESTUDIANTE = "/estudiante";
    public static final String RESENA = "/resena";
    public static final String TUTORIA_RESENA = TUTORIA + "/{id}" + RESENA;
    public static final String ESTUDIANTE_RESENA = ESTUDIANTE + "/{id}" + RESENA;
    public static final String FAVORITO = "/favorito";
    public static final String ESTUDIANTE_FAVORITO = ESTUDIANTE + "/{id}" + FAVORITO;
}
