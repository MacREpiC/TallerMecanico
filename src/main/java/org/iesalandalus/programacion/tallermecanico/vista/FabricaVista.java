package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.tallermecanico.vista.texto.VistaTexto;

public enum FabricaVista {

    TEXTO {
        @Override
        public Vista crear() {
            return new VistaTexto();
        }
    },
    VENTANAS {
        @Override
        public Vista crear(){return new VistaGrafica();}
    };

    public abstract Vista crear();
}
