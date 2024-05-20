package org.iesalandalus.programacion.tallermecanico.vista.grafica;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.TipoTrabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Trabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.vista.Vista;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.GestorEventos;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.controladores.LeerFechaFin;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controladores;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Dialogos;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.LanzadorVentanaPrincipal;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class VistaGrafica implements Vista {
    private final GestorEventos gestorEventos = new GestorEventos(Evento.values());
    public static VistaGrafica instancia;
    private Controlador ventanaPrincipal;
    public static VistaGrafica getInstancia(){
        if(instancia == null){
            instancia = new VistaGrafica();
        }
        return instancia;
    }
    @Override
    public GestorEventos getGestorEventos() {
        return gestorEventos;
    }

    @Override
    public void comenzar() {
        LanzadorVentanaPrincipal.comenzar();
    }

    public void setVentanaPrincipal(Controlador ventanaPrincipal){this.ventanaPrincipal = ventanaPrincipal;}

    @Override
    public void terminar() {
        //LanzadorVentanaPrincipal.
    }

    @Override
    public Cliente leerCliente() {
        return null;
    }

    @Override
    public Cliente leerClienteDni() {
        return null;
    }

    @Override
    public String leerNuevoNombre() {
        return null;
    }

    @Override
    public String leerNuevoTelefono() {
        return null;
    }

    @Override
    public Vehiculo leerVehiculo() {
        return null;
    }

    @Override
    public Vehiculo leerVehiculoMatricula() {
        return null;
    }

    @Override
    public Trabajo leerRevision() {
        return null;
    }

    @Override
    public Trabajo leerMecanico() {
        return null;
    }

    @Override
    public Trabajo leerTrabajoVehiculo() {
        return null;
    }

    @Override
    public int leerHoras() {
        return 0;
    }

    @Override
    public float leerPrecioMaterial() {
        return 0;
    }

    @Override
    public LocalDate leerFechaCierre() {
        LeerFechaFin leerFechaFin = (LeerFechaFin) Controladores.get("/vistas/leer/leerFechaFin.fxml", "Leer fecha fin", ventanaPrincipal.getEscenario());
        leerFechaFin.getEscenario().showAndWait();
        //return Objects.requireNonNull(leerFechaFin.getFechaFin(), "Operación cancelada por el usuario.");
        return  null;
    }

    @Override
    public LocalDate leerMes() {
        return null;
    }

    @Override
    public void notificarResultado(Evento evento, String texto, boolean exito) {
        System.out.println(evento + texto + exito);
        if(exito){
            Dialogos.mostrarDialogoInformacion(evento.toString(), texto, ventanaPrincipal.getEscenario());
        }else{
            Dialogos.mostrarDialogoError(evento.toString(), texto, ventanaPrincipal.getEscenario());
        }
    }

    @Override
    public void mostrarCliente(Cliente cliente) {

    }

    @Override
    public void mostrarVehiculo(Vehiculo vehiculo) {

    }

    @Override
    public void mostrarTrabajo(Trabajo trabajo) {

    }

    @Override
    public void mostrarClientes(List<Cliente> clientes) {

    }

    @Override
    public void mostrarVehiculos(List<Vehiculo> vehiculos) {

    }

    @Override
    public void mostrarTrabajos(List<Trabajo> trabajos) {

    }

    @Override
    public void mostrarTrabajosCliente(List<Trabajo> trabajosCliente) {

    }

    @Override
    public void mostrarTrabajosVehiculo(List<Trabajo> trabajosVehiculo) {

    }

    @Override
    public void mostrarEstadisticasMensuales(Map<TipoTrabajo, Integer> estadisticas) {

    }
}
