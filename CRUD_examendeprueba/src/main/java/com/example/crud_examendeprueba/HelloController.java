package com.example.crud_examendeprueba;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.ArrayList;
import java.util.List;

public class HelloController {

    @FXML private TextField txtNombre;
    @FXML private TextField txtTelefono;
    @FXML private ComboBox<String> cmbParentesco;
    @FXML private ListView<Contacto> listViewContactos;

    private List<Contacto> listaContactos;

    @FXML
    public void initialize() {
        listaContactos = new ArrayList<>();
        cargarParentescos();


        listViewContactos.getSelectionModel().selectedItemProperty().addListener((obs, viejo, nuevo) -> {
            if (nuevo != null) {
                txtNombre.setText(nuevo.getNombre());
                txtTelefono.setText(nuevo.getTelefono());
                cmbParentesco.setValue(nuevo.getParentesco());
            }
        });
    }

    private void cargarParentescos() {
        String[] opciones = {"Padre", "Madre", "Hermano", "Hermana", "Abuelo", "Abuela", "Tío", "Tía"};
        cmbParentesco.getItems().addAll(opciones);
    }

    @FXML
    void agregarContacto() {
        if (!validarEntradas()) return;

        String nombre = txtNombre.getText().trim();
        if (buscarPorNombre(nombre) != null) {
            mostrarAlerta("Error", "Ya existe un contacto con ese nombre.");
            return;
        }

        Contacto nuevo = new Contacto(nombre, txtTelefono.getText().trim(), cmbParentesco.getValue());
        listaContactos.add(nuevo);

        actualizarListView();
        limpiarCampos();
    }

    @FXML
    void buscarContacto() {
        String nombre = txtNombre.getText().trim();
        if (nombre.isEmpty()) {
            mostrarAlerta("Advertencia", "Ingrese un nombre para buscar.");
            return;
        }

        Contacto encontrado = buscarPorNombre(nombre);
        if (encontrado != null) {
            txtTelefono.setText(encontrado.getTelefono());
            cmbParentesco.setValue(encontrado.getParentesco());
            listViewContactos.getSelectionModel().select(encontrado);
        } else {
            mostrarAlerta("No encontrado", "No se encontró el contacto.");
            limpiarCampos();
            txtNombre.setText(nombre);
        }
    }

    @FXML
    void actualizarContacto() {
        String nombre = txtNombre.getText().trim();
        Contacto existente = buscarPorNombre(nombre);

        if (existente == null) {
            mostrarAlerta("Error", "El contacto no existe.");
            return;
        }

        if (!validarEntradas()) return;

        existente.setTelefono(txtTelefono.getText().trim());
        existente.setParentesco(cmbParentesco.getValue());

        actualizarListView();
        mostrarAlerta("Éxito", "Contacto actualizado.");
    }

    @FXML
    void eliminarContacto() {
        String nombre = txtNombre.getText().trim();
        Contacto existente = buscarPorNombre(nombre);

        if (existente != null) {
            listaContactos.remove(existente);
            actualizarListView();
            limpiarCampos();
        } else {
            mostrarAlerta("Error", "No se encontró el contacto para eliminar.");
        }
    }

    @FXML
    void limpiarCampos() {
        txtNombre.clear();
        txtTelefono.clear();
        cmbParentesco.getSelectionModel().clearSelection();
        listViewContactos.getSelectionModel().clearSelection();
    }


    private void actualizarListView() {
        listViewContactos.getItems().clear();
        listViewContactos.getItems().addAll(listaContactos);
    }

    private Contacto buscarPorNombre(String nombre) {
        for (Contacto c : listaContactos) {
            if (c.getNombre().equalsIgnoreCase(nombre)) {
                return c;
            }
        }
        return null;
    }

    private boolean validarEntradas() {
        if (txtNombre.getText().trim().isEmpty()) {
            mostrarAlerta("Validación", "El nombre no debe estar vacío.");
            return false;
        }
        String telefono = txtTelefono.getText().trim();
        if (telefono.isEmpty() || !telefono.matches("\\d{10}")) {
            mostrarAlerta("Validación", "El teléfono debe tener exactamente 10 dígitos numéricos.");
            return false;
        }
        if (cmbParentesco.getValue() == null) {
            mostrarAlerta("Validación", "Debe seleccionar un parentesco.");
            return false;
        }
        return true;
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}