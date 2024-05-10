package org.iesalandalus.programacion.tallermecanico.modelo.negocio.ficheros;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.IClientes;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.naming.OperationNotSupportedException;
import javax.xml.parsers.DocumentBuilder;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Clientes implements IClientes {
    private static final String FICHERO_CLIENTES = String.format("%s%s%s", "ficherosXml", File.separator, "clientes.xml");
    private static final String RAIZ = "ficheros";
    private static final String CLIENTE = "cliente";
    private static final String NOMBRE = "nombre";
    private static final String DNI = "dni";
    private static final String TELEFONO = "telefono";
    private static Clientes instancia;
    private final List<Cliente> coleccionClientes;

    private Clientes() {
        coleccionClientes = new ArrayList<>();
    }

    static Clientes getInstancia(){
        if(instancia == null){
            instancia = new Clientes();
        }
        return instancia;
    }

    @Override
    public void comenzar(){
        Document documentoXml = UtilidadesXml.leerDocumentoXml(FICHERO_CLIENTES);
        System.out.println("Fichero leído correctamente.");
        procesarDocumentoXml(documentoXml);
    }

    private void procesarDocumentoXml(Document documentoXml){
        if (documentoXml != null) {
            System.out.println("Fichero leído correctamente.");
            NodeList clientes = documentoXml.getElementsByTagName(CLIENTE);
            for (int i = 0; i < clientes.getLength(); i++) {
                Node cliente = clientes.item(i);
                coleccionClientes.add(getCliente((Element) cliente));
            }
        }
    }

    private Cliente getCliente(Element elemento){
        String nombre = null;
        String dni = null;
        String telefono = null;
        if (elemento != null && elemento.getNodeType() == Node.ELEMENT_NODE) {
            nombre = elemento.getAttribute(NOMBRE);
            dni = elemento.getAttribute(DNI);
            telefono = elemento.getAttribute(TELEFONO);
            System.out.println(telefono);
        }
        return new Cliente(nombre, dni, telefono);
    }

    public void terminar(){
        Document documentoXml = crearDocumentoXml();
        if (documentoXml != null) {
            documentoXml.appendChild(documentoXml.createElement(RAIZ));
            for (Cliente cliente : coleccionClientes) {
                Element elemento = getElemento(documentoXml, cliente);
                documentoXml.getDocumentElement().appendChild(elemento);
            }
        }
        UtilidadesXml.escribirDocumentoXml(documentoXml, FICHERO_CLIENTES);
    }

    private Document crearDocumentoXml(){
        DocumentBuilder constructor = UtilidadesXml.crearConstructorDocumentoXml();
        Document documentoXml = null;
        if (constructor != null){
            documentoXml = constructor.newDocument();
            documentoXml.appendChild(documentoXml.createElement("clientes"));
            for(Cliente cliente : coleccionClientes){
                Element elementoCliente = getElemento(documentoXml, cliente);
                documentoXml.getDocumentElement().appendChild(elementoCliente);
            }
        }
        return documentoXml;
    }

    private Element getElemento(Document documentoXml, Cliente cliente){
        Element elementoCliente = documentoXml.createElement(CLIENTE);
        elementoCliente.setAttribute(NOMBRE, cliente.getNombre());
        elementoCliente.setAttribute(DNI, cliente.getDni());
        elementoCliente.setAttribute(TELEFONO, cliente.getTelefono());
        return elementoCliente;
    }

    @Override
    public List<Cliente> get() {
        return new ArrayList<>(coleccionClientes);
    }

    @Override
    public void insertar(Cliente cliente) throws OperationNotSupportedException {
        Objects.requireNonNull(cliente, "No se puede insertar un cliente nulo.");
        if (coleccionClientes.contains(cliente)) {
            throw new OperationNotSupportedException("Ya existe un cliente con ese DNI.");
        }
        coleccionClientes.add(cliente);
    }

    @Override
    public boolean modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
        Objects.requireNonNull(cliente, "No se puede modificar un cliente nulo.");
        Cliente clienteEncontrado = buscar(cliente);
        if (clienteEncontrado == null) {
            throw new OperationNotSupportedException("No existe ningún cliente con ese DNI.");
        }
        boolean modificado = false;
        if (nombre != null && !nombre.isBlank()) {
            clienteEncontrado.setNombre(nombre);
            modificado = true;
        }
        if (telefono != null && !telefono.isBlank()) {
            clienteEncontrado.setTelefono(telefono);
            modificado = true;
        }
        return modificado;
    }

    @Override
    public Cliente buscar(Cliente cliente) {
        Objects.requireNonNull(cliente, "No se puede buscar un cliente nulo.");
        int indice = coleccionClientes.indexOf(cliente);
        return (indice == -1) ? null : coleccionClientes.get(indice);
    }

    @Override
    public void borrar(Cliente cliente) throws OperationNotSupportedException {
        Objects.requireNonNull(cliente, "No se puede borrar un cliente nulo.");
        if (!coleccionClientes.contains(cliente)) {
            throw new OperationNotSupportedException("No existe ningún cliente con ese DNI.");
        }
        coleccionClientes.remove(cliente);
    }
}
