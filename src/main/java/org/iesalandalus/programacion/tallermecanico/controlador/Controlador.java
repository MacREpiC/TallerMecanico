package org.iesalandalus.programacion.tallermecanico.controlador;

import org.iesalandalus.programacion.tallermecanico.modelo.cascada.ModeloCascada;
import org.iesalandalus.programacion.tallermecanico.vista.Vista;
import org.iesalandalus.programacion.tallermecanico.vista.texto.VistaTexto;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;

import javax.naming.OperationNotSupportedException;
import java.util.Objects;

public class Controlador implements IControlador {
    private final Vista vista;
    private final ModeloCascada modelo;


    public Controlador(ModeloCascada modelo, VistaTexto vista) {
        Objects.requireNonNull(modelo, "El modelo no puede ser nulo.");
        Objects.requireNonNull(vista, "La vista no puede ser nula.");
        this.modelo = modelo;
        this.vista = vista;
    }

    @Override
    public void comenzar() throws OperationNotSupportedException {
        modelo.comenzar();
        vista.comenzar();
    }

    @Override
    public void terminar() {
        modelo.terminar();
        vista.terminar();
    }

    @Override
    public void actualizar(Evento evento) throws OperationNotSupportedException {
        try {
            switch (evento){
                case INSERTAR_CLIENTE -> modelo.insertar(vista.leerCliente());
                case BUSCAR_CLIENTE -> modelo.buscar(vista.leerCliente());
                case BORRAR_CLIENTE -> modelo.borrar(vista.leerCliente());
                case LISTAR_CLIENTES -> vista.mostrar
            }
        } catch ()

    }
}