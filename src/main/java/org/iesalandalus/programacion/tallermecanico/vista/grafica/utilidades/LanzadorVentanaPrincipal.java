package org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades;

import javafx.application.Application;
import javafx.stage.Stage;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.VistaGrafica;

public class LanzadorVentanaPrincipal extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Controlador ventanaPrincipal = Controladores.get("/vistas/VentanaPrincipal.fxml", "Taller Mecánico", null);
        ventanaPrincipal.addHojaEstilos("/estilos/aplicacion.css");
        ventanaPrincipal.addIcono("/imagenes/iconoSaludar.png");
        VistaGrafica.getInstancia().setVentanaPrincipal(ventanaPrincipal);
        ventanaPrincipal.getEscenario().show();
    }

    public static void comenzar(){launch(LanzadorVentanaPrincipal.class);}
    public static void salir(){LanzadorVentanaPrincipal.}
}