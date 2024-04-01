package org.iesalandalus.programacion.tallermecanico.controlador;

import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;

import javax.naming.OperationNotSupportedException;

public interface IControlador extends org.iesalandalus.programacion.tallermecanico.vista.eventos.ReceptorEventos {
    void comenzar() throws OperationNotSupportedException;

    void terminar();

}
