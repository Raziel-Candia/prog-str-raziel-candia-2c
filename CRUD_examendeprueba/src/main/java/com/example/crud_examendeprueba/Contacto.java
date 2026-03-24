package com.example.crud_examendeprueba;

public class Contacto {
    private String nombre;
    private String telefono;
    private String parentesco;

    public Contacto(String nombre, String telefono, String parentesco) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.parentesco = parentesco;
    }

    // Getters y Setters
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getParentesco() { return parentesco; }
    public void setParentesco(String parentesco) { this.parentesco = parentesco; }

    @Override
    public String toString() {
        return String.format("Nombre: %s | Teléfono: %s | Parentesco: %s", nombre, telefono, parentesco);
    }
}
