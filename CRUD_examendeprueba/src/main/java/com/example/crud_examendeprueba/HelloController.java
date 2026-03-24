package com.example.crud_examendeprueba;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.ArrayList;
import java.util.List;

public class HelloController {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtTelefono;
    @FXML
    private ComboBox<String> cmbParentesco;
    @FXML
    private ListView<Contacto> lvContactos;


    private List<Contacto> listaContactos;
    private final String[] ARREGLO_PARENTESCOS = {"Padre", "Madre", "Hermano", "Hermana", "Abuelo", "Abuela", "Tío", "Tía"};


    @FXML
    public void initialize() {
        listaContactos = new ArrayList<>();
        cargarOpcionesParentesco();
    }


    private void cargarOpcionesParentesco() {
        cmbParentesco.getItems().addAll(ARREGLO_PARENTESCOS);
    }


    private boolean validarDatos(String nombre, String telefono, String parentesco, boolean esActualizacion) {
        if (nombre.isEmpty()) {
            mostrarMensaje("Error", "El nombre no puede estar vacío");
            return false;
        }
        if (telefono.isEmpty() || !telefono.matches("\\d{10}")) {
            mostrarMensaje("Error", "El teléfono debe tener exactamente 10 dígitos");
            return false;
        }
        if (parentesco == null || parentesco.isEmpty()) {
            mostrarMensaje("Error", "Debe seleccionar un parentesco");
            return false;
        }

        if (!esActualizacion) {
            for (Contacto c : listaContactos) {
                if (c.getNombre().equalsIgnoreCase(nombre)) {
                    mostrarMensaje("Error", "Ya existe un contacto con ese nombre");
                    return false;
                }
            }
        }
        return true;
    }


    @FXML
    private void agregarContacto() {
        String nombre = txtNombre.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String parentesco = cmbParentesco.getValue();

        if (validarDatos(nombre, telefono, parentesco, false)) {
            listaContactos.add(new Contacto(nombre, telefono, parentesco));
            actualizarListView();
            limpiarCampos();
            mostrarMensaje("Éxito", "Contacto agregado correctamente");
        }
    }


    @FXML
    private void buscarContacto() {
        String nombreBuscado = txtNombre.getText().trim();
        if (nombreBuscado.isEmpty()) {
            mostrarMensaje("Advertencia", "Ingrese un nombre para buscar");
            return;
        }

        for (Contacto c : listaContactos) {
            if (c.getNombre().equalsIgnoreCase(nombreBuscado)) {
                txtNombre.setText(c.getNombre());
                txtTelefono.setText(c.getTelefono());
                cmbParentesco.setValue(c.getParentesco());
                return;
            }
        }
        mostrarMensaje("Información", "No se encontró el contacto");
        limpiarCampos();
    }


    @FXML
    private void actualizarContacto() {
        String nombreActual = txtNombre.getText().trim();
        String nuevoTelefono = txtTelefono.getText().trim();
        String nuevoParentesco = cmbParentesco.getValue();

        if (validarDatos(nombreActual, nuevoTelefono, nuevoParentesco, true)) {
            for (Contacto c : listaContactos) {
                if (c.getNombre().equalsIgnoreCase(nombreActual)) {
                    c.setTelefono(nuevoTelefono);
                    c.setParentesco(nuevoParentesco);
                    actualizarListView();
                    limpiarCampos();
                    mostrarMensaje("Éxito", "Contacto actualizado correctamente");
                    return;
                }
            }
            mostrarMensaje("Error", "No se encontró el contacto para actualizar");
        }
    }


    @FXML
    private void eliminarContacto() {
        String nombreEliminar = txtNombre.getText().trim();
        if (nombreEliminar.isEmpty()) {
            mostrarMensaje("Advertencia", "Ingrese o busque un contacto para eliminar");
            return;
        }

        for (Contacto c : listaContactos) {
            if (c.getNombre().equalsIgnoreCase(nombreEliminar)) {
                listaContactos.remove(c);
                actualizarListView();
                limpiarCampos();
                mostrarMensaje("Éxito", "Contacto eliminado correctamente");
                return;
            }
        }
        mostrarMensaje("Error", "No se encontró el contacto para eliminar");
    }


    @FXML
    private void limpiarCampos() {
        txtNombre.clear();
        txtTelefono.clear();
        cmbParentesco.setValue(null);
    }


    private void actualizarListView() {
        lvContactos.getItems().clear();
        lvContactos.getItems().addAll(listaContactos);
    }

    private void mostrarMensaje(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
