package org.iesalandalus.programacion.tallermecanico.vista.grafica.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controladores;

public class VentanaPrincipal extends Controlador {

    @FXML
    private HBox hboxClientes;

    @FXML
    private HBox hboxTrabajos;

    @FXML
    private HBox hboxVehiculos;
    @FXML
    void insertarCliente() {
        InsertarCliente insertarCliente = (InsertarCliente) Controladores.get("/vistas/insertarCliente.fxml", "Insertar cliente", getEscenario());
        insertarCliente.addHojaEstilos("/estilos/insertarCliente.css");
        insertarCliente.limpiarCampos();
        insertarCliente.getEscenario().showAndWait();
        if (!insertarCliente.isCancelado()) {
            VistaGrafica.getInstancia().getGestorEventos().notificar(Evento.INSERTAR_CLIENTE);
        }
    }

    @FXML
    void buscarCliente() {
        LeerClienteDni vistaClienteDni = (LeerClienteDni) Controladores.get("/vistas/leerClienteDni.fxml", "Leer dni", getEscenario());
        vistaClienteDni.addHojaEstilos("/estilos/leerClienteDni.css");
        vistaClienteDni.limpiarCampos();
        vistaClienteDni.getEscenario().showAndWait();
        if (!vistaClienteDni.isCancelado()) {
            VistaGrafica.getInstancia().getGestorEventos().notificar(Evento.BUSCAR_CLIENTE);
        }
    }

    @FXML
    void listarClientes() {

    }

    @FXML
    void insertarVehiculo() {

    }

    @FXML
    void buscarVehiculo() {

    }

    @FXML
    void listarVehiculos() {

    }

    @FXML
    void insertarTrabajo(ActionEvent event) {

    }

    @FXML
    void buscarTrabajo(ActionEvent event) {

    }

    @FXML
    void listarTrabajos(ActionEvent event) {

    }
}
