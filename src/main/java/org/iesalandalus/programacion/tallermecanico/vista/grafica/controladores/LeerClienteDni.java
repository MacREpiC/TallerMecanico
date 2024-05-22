package org.iesalandalus.programacion.tallermecanico.vista.grafica.controladores;

import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controles;

public class LeerClienteDni extends Controlador {
    @FXML
    private ResourceBundle resources;

    @FXML
    private Button btAceptar;

    @FXML
    private Button btCancelar;

    @FXML
    private Button btInsertar;

    @FXML
    private TextField tfDni;

    private boolean esCancelado;
    @FXML
    void aceptar() {
        esCancelado = false;
        getEscenario().close();
    }

    @FXML
    void cancelar() {
        esCancelado = true;
        getEscenario().close();
    }

    @FXML
    void insertar() {
        tfDni.setText("77695741Q");
    }

    public boolean isCancelado() {
        return esCancelado;
    }

    public void limpiarCampos() {
        esCancelado = true;
        Controles.limpiarCamposTexto(tfDni);
        borrrarEstilos();
    }

    private void borrrarEstilos() {
        tfDni.getStyleClass().removeAll("error", "bien");
    }

    private boolean dniValido(String dni) {
        return dni.matches(Cliente.ER_DNI);
    }

    @FXML
    private void initialize() {
        tfDni.textProperty().addListener((observable, oldValue, newValue) -> {
            tfDni.getStyleClass().removeAll("error", "bien");
            if(newValue.isEmpty()){
                tfDni.getStyleClass().add("error");
            }else if(!dniValido(newValue)) {
                tfDni.getStyleClass().add("error");
            }else {
                tfDni.getStyleClass().add("bien");
            }
        });
    }
}
