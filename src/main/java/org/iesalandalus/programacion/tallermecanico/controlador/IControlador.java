package org.iesalandalus.programacion.tallermecanico.controlador;

import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;

public interface IControlador extends org.iesalandalus.programacion.tallermecanico.vista.eventos.ReceptorEventos {
    void comenzar();

    void terminar();

}
