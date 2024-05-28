package org.iesalandalus.programacion.tallermecanico.vista.grafica.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controles;

public class InsertarCliente extends Controlador {

    @FXML
    private Button btAceptar;

    @FXML
    private Button btCancelar;

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfDni;

    @FXML
    private TextField tfTelefono;

    @FXML
    private Button btInsertar;

    private boolean esCancelado;

    public Cliente getCliente(){
        String nombre = tfNombre.getText();
        String dni = tfDni.getText();
        String telefono = tfTelefono.getText();
        return new Cliente(nombre, dni, telefono);
    }

    @FXML
    void insertar() {
        tfNombre.setText("Alejandro");
        tfDni.setText("77695741Q");
        tfTelefono.setText("644901932");
    }

    public boolean isCancelado() {
        return esCancelado;
    }

    public void limpiarCampos() {
        esCancelado = true;
        Controles.limpiarCamposTexto(tfDni,tfNombre,tfTelefono);
        borrrarEstilos();
    }

    private void borrrarEstilos() {
        tfDni.getStyleClass().removeAll("error", "bien");
        tfNombre.getStyleClass().removeAll("error", "bien");
        tfTelefono.getStyleClass().removeAll("error", "bien");
    }

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

    private boolean dniValido(String dni) {
        return dni.matches(Cliente.ER_DNI);
    }

    private boolean nombreValido(String nombre) {
        return nombre.matches(Cliente.ER_NOMBRE);
    }

    private boolean telefonoValido(String telefono){
        return telefono.matches(Cliente.ER_TELEFONO);
    }

    @FXML
    private void initialize() {
        tfNombre.textProperty().addListener((observable, oldValue, newValue) -> {
            tfNombre.getStyleClass().removeAll("error", "bien");
            if(newValue.isEmpty()){
                tfNombre.getStyleClass().add("error");
            }else if(!nombreValido(newValue)) {
                tfNombre.getStyleClass().add("error");
            }else{
                tfNombre.getStyleClass().add("bien");
            }
        });

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

        tfTelefono.textProperty().addListener((observable, oldValue, newValue) -> {
            tfTelefono.getStyleClass().removeAll("error", "bien");
            if(newValue.isEmpty()){
                tfTelefono.getStyleClass().add("error");
            }else if(!telefonoValido(newValue)) {
                tfTelefono.getStyleClass().add("error");
            } else {
                tfTelefono.getStyleClass().add("bien");
            }
        });
    }
}