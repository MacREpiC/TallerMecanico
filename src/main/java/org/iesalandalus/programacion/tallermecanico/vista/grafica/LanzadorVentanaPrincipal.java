package org.iesalandalus.programacion.tallermecanico.vista.grafica;

import javafx.application.Application;
import javafx.stage.Stage;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controladores;

public class LanzadorVentanaPrincipal extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Controlador ventanaPrincipal = Controladores.get("/vistas/ventanaPrincipal.fxml", "Taller Mecánico", null);
        ventanaPrincipal.addHojaEstilos("/estilos/ventanaPrincipal.css");
        ventanaPrincipal.addIcono("/imagenes/iconoSaludar.png");
        VistaGrafica.getInstancia().setVentanaPrincipal(ventanaPrincipal);
        ventanaPrincipal.getEscenario().show();
    }

    public static void comenzar(){launch(LanzadorVentanaPrincipal.class);}
}