package com.digitalhouse.CoachConnectBE.controller.exceptionHandler;

import com.digitalhouse.CoachConnectBE.controller.exceptionHandler.dto.ErrorDTO;
import com.digitalhouse.CoachConnectBE.service.exception.RecursoConDependenciasException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.digitalhouse.CoachConnectBE.service.exception.RecursoNoEncontradoException;

@Log4j2
@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler({RecursoNoEncontradoException.class})
    public ResponseEntity<ErrorDTO> RecursoNoEncontradoHandler(RecursoNoEncontradoException exception) {
        String mensaje = "No se encontro el recurso";

        logException(exception, mensaje);

        ErrorDTO response = new ErrorDTO(HttpStatus.NOT_FOUND.getReasonPhrase(), mensaje);

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({RecursoConDependenciasException.class})
    public ResponseEntity<ErrorDTO> RecursoConDependenciasException(RecursoConDependenciasException exception) {
        String mensaje = "El recurso no se puede eliminar dado que esta en uso";

        logException(exception, mensaje);

        ErrorDTO response = new ErrorDTO(HttpStatus.CONFLICT.getReasonPhrase(), mensaje);

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    private void logException(RuntimeException exception, String mensajeRespuesta) {
        log.error("Se atrapo una excepcion de tipo " + exception.getClass() + ", con el mensaje " + exception.getMessage() + ", se respondera con el mensaje: " + mensajeRespuesta, exception);
    }
}
