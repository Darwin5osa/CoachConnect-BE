package com.digitalhouse.CoachConnectBE.service;

import com.digitalhouse.CoachConnectBE.service.exception.RecursoNoEncontradoException;

public class ServiceExtension {
    private static final int NINGUN_ELEMENTO_MODIFICADO = 0;

    public static void checkearCantidadModificacion(Integer elementosModificados) {
        if (elementosModificados == NINGUN_ELEMENTO_MODIFICADO) {
            throw new RecursoNoEncontradoException();
        }
    }
}
